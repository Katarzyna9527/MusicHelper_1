package com.music_helper.musichelper.Model;

public class CalculateTriad {
    public static String calculate(int inter_1, int inter_2){ //inter in halftones
        String triad = Triads.getTriadByIntervals(inter_1, inter_2);
        return triad;
    }
}
