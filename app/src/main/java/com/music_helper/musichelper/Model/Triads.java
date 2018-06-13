package com.music_helper.musichelper.Model;

public enum Triads {
    dur (4,3,-3,-4,"dur postać zasadnicza"),
    dur_1 (3,5,-5,-3, "dur pierwszy przewrót"),
    dur_2 (5,4,-4,-5, "dur drugi przewrót"),
    moll (3,4,-4,-3,"moll postać zasadnicza"),
    moll_1 (4,5,-5,-4, "moll pierwszy przewrót"),
    moll_2 (5,3,-3,-5, "moll drugi przewrót"),
    zw (4,4,-4,-4, "zwiększony"),
    zm (3,3,-3,-3, "zmniejszony");

    private final int interval_1_up;
    private final int interval_2_up;
    private final int interval_1_down;
    private final int interval_2_down;
    private final String name;

    Triads(int interval_1_up, int interval_2_up, int interval_1_down, int interval_2_down, String name) {
        this.interval_1_up = interval_1_up;
        this.interval_2_up = interval_2_up;
        this.interval_1_down = interval_1_down;
        this.interval_2_down = interval_2_down;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getTriadByIntervals(int inter_1, int inter_2){
        for(Triads i : Triads.values()){
            if((inter_1 == i.interval_1_up && inter_2 == i.interval_2_up) || (inter_1 == i.interval_1_down && inter_2 == i.interval_2_down)) return i.getName();
        }
        return null;
    }
}
