package com.music_helper.musichelper.Model;

public enum Intervals {

    pryma (0,"pryma"),
    sekunda_mala (1, "sekuna mała"),
    sekunda_wileka (2, "sekuna wielka"),
    tercja_mala (3, "tercja mała"),
    tercja_wielka (4, "tercja wielka"),
    kwarta_czysta (5, "kwarta czysta"),
    tryton (6, "tryton"),
    kwinta_czysta (7, "kwinta czysta"),
    seksta_mala (8, "seksta mała"),
    seksta_wielka (9, "seksta wileka"),
    septyma_mala (10, "septyma mała"),
    septyma_wielka (11, "septyma wielka"),
    oktawa (12, "oktawa");

    private final int halftone_number;
    private final String name;

    Intervals(int halftone_number, String name){
        this.halftone_number = halftone_number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHalftone_number() {
        return halftone_number;
    }

    public static String getIntervalByHalftones(int code){
        for(Intervals i : Intervals.values()){
            if(code == i.halftone_number) return i.getName();
        }
        return null;
    }
}
