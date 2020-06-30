package com.sdk.studydemo.shuju.arry;

public class ArrayList {
    private int size;
    private int[] elements;

    private static final int DEFAULT_CAPTICY = 10;

    public ArrayList(int capaticy) {
        capaticy = (capaticy < DEFAULT_CAPTICY) ? DEFAULT_CAPTICY : capaticy;
        elements = new int[capaticy];
    }

    public ArrayList() {
        this(DEFAULT_CAPTICY);
    }

}
