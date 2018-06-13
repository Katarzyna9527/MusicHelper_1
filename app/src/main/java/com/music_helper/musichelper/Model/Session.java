package com.music_helper.musichelper.Model;

public abstract class Session {

    public SoundRecorder soundRecorder;
    boolean firtsRecorded = false;
    boolean secondRecorded = false;
    boolean recognise = false;

    public SoundRecorder getSoundRecorder() {
        return soundRecorder;
    }

    public boolean isFirtsRecorded() {
        return firtsRecorded;
    }

    public void setFirtsRecorded(boolean firtsRecorded) {
        this.firtsRecorded = firtsRecorded;
    }

    public void setSecondRecorded(boolean secondRecorded) {
        this.secondRecorded = secondRecorded;
    }

    public boolean isSecondRecorded() {
        return secondRecorded;
    }

    public void setRecognise(boolean recognise) {
        this.recognise = recognise;
    }

    public void actualizeSession(){
        if(isFirtsRecorded() == false) setFirtsRecorded(true);
        else if(isSecondRecorded() == false) setSecondRecorded(true);
        else setRecognise(true);
    }

    public void refreshSession(){
        setFirtsRecorded(false);
        setSecondRecorded(false);
        setRecognise(false);
    }

    public boolean isRecognise() {
        return recognise;
    }


}
