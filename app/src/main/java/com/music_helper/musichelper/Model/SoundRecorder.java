package com.music_helper.musichelper.Model;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SoundRecorder {
    private static String TAG = "Recorder";
    private boolean currentlySendingAudio = false;
    private List<Double> list;

    private static final int channel_config = AudioFormat.CHANNEL_IN_MONO;
    private static final int format = AudioFormat.ENCODING_PCM_16BIT;
    private static final int sampleSize = 44100;
    private static final int bufferSize = AudioRecord.getMinBufferSize(sampleSize, channel_config, format);
    private AudioRecord recorder;
    short[] buffer;
    int bufferSizeInBytes = 1024;
    //private Thread recordingThread = null;
    //private boolean isRecording = false;

    //int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
    //int BytesPerElement = 2; // 2 bytes in 16bit format

    public SoundRecorder() {
        this.recorder  = new AudioRecord(MediaRecorder.AudioSource.MIC,
                sampleSize, channel_config,
                format, bufferSize);
        this.list = new ArrayList<>();
    }


    private void startRecording(){
        Thread streamThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                   // Log.d(TAG, "Creating the buffer of size " + bufferSizeInBytes);
                    buffer = new short[bufferSizeInBytes];

                    Log.d(TAG, "Creating the AudioRecord");/*
                    recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                            sampleSize, channel_config, format, bufferSize);*/

                   // Log.d(TAG, "AudioRecord recording...");
                    recorder.startRecording();

                    double freq;
                    while (currentlySendingAudio == true) {
                       // Log.d(TAG, "LOLOLOLOLO");
                        // read the data into the buffer
                        int read = recorder.read(buffer, 0, bufferSizeInBytes);
                        if(read>0){
                            freq = calculate();
                            Log.d(TAG, "czestotliwosc " + freq);
                        }
                        /*// place contents of buffer into the packet
                        packet = new DatagramPacket(buffer, read,
                                serverAddress, PORT);

                        // send the packet
                        socket.send(packet);*/
                    }
                   // Log.d(TAG, "AudioRecord finished recording");

                } catch (Exception e) {
                   // Log.e(TAG, "Exception: " + e);
                }
            }
        });

        // start the thread
        streamThread.start();
    }


    public void startRecordingAudio() {

       // Log.i(TAG, "Starting the audio stream");
        currentlySendingAudio = true;
        startRecording();

    }

    public void stopRecordingAudio() {

       // Log.i(TAG, "Stopping the audio stream average = " );
        currentlySendingAudio = false;
        recorder.stop();

    }

    public double getSoundFrequency(){
        double average = 0;
        for (Double item : list) {
            average += item;
        }
        average = average/list.size();


        double max_elem = 0;
        int max_number_of_elems = 0;
        Set<Double> uniqueSet = new HashSet<Double>(list);
        for (Double temp : uniqueSet) {
            if(Collections.frequency(list, temp)> max_number_of_elems){
                max_elem = temp;
                max_number_of_elems = Collections.frequency(list, temp);
            }
        }

        list.clear();


        Log.d(TAG, "Masakra" + max_elem);
        return max_elem;
    }

    public double calculate() {

        double[] magnitude = new double[bufferSizeInBytes/2];

        //Create Complex array for use in FFT
        Complex[] fftTempArray = new Complex[bufferSizeInBytes];
        for (int i = 0; i < bufferSizeInBytes; i++) {
            fftTempArray[i] = new Complex(buffer[i], 0);
        }

        //Obtain array of FFT data
        final Complex[] fftArray = FFT.fft(fftTempArray);

        // calculate power spectrum (magnitude) values from fft[]
        for (int i = 0; i < (bufferSizeInBytes / 2) - 1; ++i) {

            double real = fftArray[i].re();
            double imaginary = fftArray[i].im();
            magnitude[i] = Math.sqrt(real * real + imaginary * imaginary);

        }

        // find largest peak in power spectrum
        double max_magnitude = magnitude[0];
        int max_index = 0;
        for (int i = 0; i < magnitude.length; ++i) {
            if (magnitude[i] > max_magnitude) {
                max_magnitude = (int) magnitude[i];
                max_index = i;

            }
        }

        for (int i = 0; i < magnitude.length; ++i) {
            if (magnitude[i] < magnitude[max_index]*0.5) {
                magnitude[i] = 0;
            }
        }

        double max_2 = magnitude[max_index]*0.5;
        magnitude[max_index] = max_2;

        max_magnitude = magnitude[0];
        max_index = 0;
        for (int i = 0; i < magnitude.length; ++i) {
            if (magnitude[i] > 0) {
                max_magnitude =  magnitude[i];
                max_index = i;
                break;
            }
        }

      /*  List<Double> list_pom = new ArrayList<>();
        for(int i = max_index; i<magnitude.length; ++i ){
            if (magnitude[i]!=0){
                list_pom.add(magnitude[i]);
            }
            else break;
        }

        double max = Collections.max(list_pom);

        int z=0;
        for(double elem:list_pom){
            if(elem == max) break;
            z++;
        }

        max_index = max_index+z;*/

        double freq = sampleSize * max_index / bufferSizeInBytes; //frequency in hz
        list.add(freq);
        return freq;
    }



}
