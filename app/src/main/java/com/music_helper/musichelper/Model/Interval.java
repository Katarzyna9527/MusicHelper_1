package com.music_helper.musichelper.Model;

public class Interval {
    private double firstSound = 0.0;
    private double SecondSound = 0.0;
    private int halftones;
    private Intervals name;

    public Interval() {
    }

    public void setFirstSound(double firstSound) {
        this.firstSound = firstSound;
    }

    public void setSecondSound(double secondSound) {
        SecondSound = secondSound;
    }

    public void setName(Intervals name) {
        this.name = name;
    }

    public double getFirstSound() {
        return firstSound;
    }

    public double getSecondSound() {
        return SecondSound;
    }

    public Intervals getName() {
        return name;
    }

    public int getHalftones() {
        return halftones;
    }

    public void setHalftones(int halftones) {
        this.halftones = halftones;
    }
}
