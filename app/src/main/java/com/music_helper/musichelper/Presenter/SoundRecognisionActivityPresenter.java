package com.music_helper.musichelper.Presenter;

import android.util.Log;

import com.music_helper.musichelper.Model.CalculateInterval;
import com.music_helper.musichelper.Model.CalculateTriad;
import com.music_helper.musichelper.Model.IntervalSession;
import com.music_helper.musichelper.Model.Intervals;
import com.music_helper.musichelper.Model.TriadSession;
import com.music_helper.musichelper.R;
import com.music_helper.musichelper.View.MainActivity;
import com.music_helper.musichelper.View.SoundRecognisionActivity;

public class SoundRecognisionActivityPresenter {

    private SoundRecognisionActivity view;

    public static final int IntervalMode = R.id.button_interval_recognision;
    public static final int TriadMode = R.id.button_triad_recognision;

    private double soundFrequency;
    private IntervalSession intervalSession;
    private TriadSession triadSession;
    boolean intervalSession_flag = false; // if interval then true if triad then false
    private String titleDialog = "Rozpoznano interwał!!!";
    private String messageDialog;
    private boolean button_start_enable = true;
    private boolean button_stop_enable = false;

    public SoundRecognisionActivityPresenter(SoundRecognisionActivity view, int mode) {
        this.view = view;
        if(mode == IntervalMode) {
            this.intervalSession = new IntervalSession();
            intervalSession_flag = true;
        }
        if(mode == TriadMode)
            this.triadSession = new TriadSession();
    }

    public void buttonStartRecordingClicked(){
        if(button_start_enable) {
            if (intervalSession_flag)
                startRecordingClickedIntervalSession();
            else
                startRecordingClickedTriadSession();
            view.showInfoTextView("Recording...");
            button_stop_enable = true;
            button_start_enable = false;
            view.animation();
        }
    }

    public void buttonStopRecordingClicked(){
        if(button_stop_enable) {
            if (intervalSession_flag)
                stopRecordingClickedIntervalSession();
            else
                stopRecordingClickedTriadSession();
            button_stop_enable = false;
            button_start_enable = true;
            view.animationStop();
        }
    }

    public void setSoundFrequency(double soundFrequency) {
        this.soundFrequency = soundFrequency;
    }

    public int recogniseInterval(double freq_0, double freq_n){
        int pom = 0;
        pom = CalculateInterval.calculate(freq_0, freq_n);
       /* Log.i("ll", "First " + freq_0 );
        Log.i("ll", "Second " + freq_n );
        Log.i("ll", "Oby działało " + pom );
        messageDialog = "Rozpoznany interwał to: " + Intervals.getIntervalByHalftones(Math.abs(pom)) + ".";*/
        return pom;
    }


    public void showIntervalResultDialog(){
        titleDialog = "Rozpoznano interwał!!!";
        view.showInformDialog(titleDialog,messageDialog);
    }

    public void showTriadResultDialog(){
        titleDialog = "Rozpoznano trójdźwięk!!!";
        view.showInformDialog(titleDialog,messageDialog);
    }

    public void startRecordingClickedIntervalSession(){
        intervalSession.getSoundRecorder().startRecordingAudio();
    }

    public void startRecordingClickedTriadSession(){
        triadSession.getSoundRecorder().startRecordingAudio();

    }

    public void stopRecordingClickedIntervalSession(){
        intervalSession.getSoundRecorder().stopRecordingAudio();
        setSoundFrequency(intervalSession.getSoundRecorder().getSoundFrequency());
        intervalSession.actualizeSession();
        if(intervalSession.isFirtsRecorded() && !intervalSession.isSecondRecorded()){
            intervalSession.getInterval().setFirstSound(soundFrequency);
            view.showInfoTextView("Podaj drugi dzwięk :-)");
        }
        if(intervalSession.isFirtsRecorded() && intervalSession.isSecondRecorded()){
            intervalSession.getInterval().setSecondSound(soundFrequency);
            intervalSession.actualizeSession();
        }
        if(intervalSession.isRecognise()){
            int pom = recogniseInterval(intervalSession.getInterval().getFirstSound(),intervalSession.getInterval().getSecondSound());
            intervalSession.getInterval().setHalftones(pom);
            messageDialog = "Rozpoznany interwał to: " + Intervals.getIntervalByHalftones(Math.abs(pom)) + ".";
            showIntervalResultDialog();
            intervalSession.refreshSession();
        }
    }

    public void stopRecordingClickedTriadSession(){
        triadSession.getSoundRecorder().stopRecordingAudio();
        setSoundFrequency(triadSession.getSoundRecorder().getSoundFrequency());
        triadSession.actualizeSession();
        if(triadSession.isFirtsRecorded() && !triadSession.isSecondRecorded() && !triadSession.isThirdRecorded()){
            triadSession.getFirstInterval().setFirstSound(soundFrequency);
            view.showInfoTextView("Podaj drugi dzwięk :-)");
        }
        if(triadSession.isFirtsRecorded() && triadSession.isSecondRecorded() && !triadSession.isThirdRecorded()){
            triadSession.getFirstInterval().setSecondSound(soundFrequency);
            triadSession.getSecondInterval().setFirstSound(soundFrequency);
            view.showInfoTextView("Podaj trzeci dzwięk :-)");
        }
        if(triadSession.isFirtsRecorded() && triadSession.isSecondRecorded() && triadSession.isThirdRecorded()){
            triadSession.getSecondInterval().setSecondSound(soundFrequency);
            triadSession.actualizeSession();
        }
        if(triadSession.isRecognise()){
            int pom_1 = recogniseInterval(triadSession.getFirstInterval().getFirstSound(),triadSession.getFirstInterval().getSecondSound());
            int pom_2 = recogniseInterval(triadSession.getSecondInterval().getFirstSound(),triadSession.getSecondInterval().getSecondSound());
            triadSession.getFirstInterval().setHalftones(pom_1);
            triadSession.getSecondInterval().setHalftones(pom_2);
            String pom = CalculateTriad.calculate(pom_1,pom_2);
            messageDialog = "Rozpoznany trójdżwięk to: " + pom + ".";
            showTriadResultDialog();
            triadSession.refreshSession();
        }
    }

}
