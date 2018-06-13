package com.music_helper.musichelper.Model;

public class IntervalSession extends Session{

    private Interval interval;

    public IntervalSession() {
        soundRecorder = new SoundRecorder();
        this.interval = new Interval();
    }

    public Interval getInterval() {
        return interval;
    }


    //boolean firtsRecorded = false;
    //boolean secondRecorded = false;
    //boolean recogniseInterval = false;



    /*public SoundRecorder getSoundRecorder() {
        return soundRecorder;
    }*/

/*
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

    public void setRecogniseInterval(boolean recogniseInterval) {
        this.recogniseInterval = recogniseInterval;
    }

    public void actualizeSession(){
        if(isFirtsRecorded() == false) setFirtsRecorded(true);
        else if(isSecondRecorded() == false) setSecondRecorded(true);
        else setRecogniseInterval(true);
    }

    public void refreshSession(){
        setFirtsRecorded(false);
        setSecondRecorded(false);
        setRecogniseInterval(false);
    }

    public boolean isRecogniseInterval() {
        return recogniseInterval;
    }*/
}
