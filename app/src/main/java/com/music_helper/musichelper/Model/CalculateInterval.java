package com.music_helper.musichelper.Model;

public class CalculateInterval {
    public static int calculate(double freq_0, double freq_n){
        int halftonesNumber;
        double value = Math.pow(freq_n/freq_0,12);
        double base = 2.0;
        double halftonesNumber_double = Math.log(value)/Math.log(base);

        return halftonesNumber = (int) Math.round(halftonesNumber_double);
    }
}
