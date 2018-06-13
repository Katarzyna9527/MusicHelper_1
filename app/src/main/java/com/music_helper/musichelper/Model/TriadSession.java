package com.music_helper.musichelper.Model;

public class TriadSession extends Session{
    private Interval firstInterval;
    private Interval secondInterval;
    boolean thirdRecorded = false;

    public TriadSession() {
        soundRecorder = new SoundRecorder();
        this.firstInterval = new Interval();
        this.secondInterval = new Interval();
    }

    public Interval getFirstInterval() {
        return firstInterval;
    }

    public Interval getSecondInterval() {
        return secondInterval;
    }

    public boolean isThirdRecorded() {
        return thirdRecorded;
    }

    public void setThirdRecorded(boolean thirdRecorded) {
        this.thirdRecorded = thirdRecorded;
    }

    @Override
    public void actualizeSession(){
        if(isFirtsRecorded() == false) setFirtsRecorded(true);
        else if(isSecondRecorded() == false) setSecondRecorded(true);
        else if(isThirdRecorded() == false) setThirdRecorded(true);
        else setRecognise(true);
    }

    @Override
    public void refreshSession(){
        super.refreshSession();
        setThirdRecorded(false);
    }
}
