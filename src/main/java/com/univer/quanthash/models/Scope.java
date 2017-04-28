package com.univer.quanthash.models;

/**
 * Created by ASUS-PC on 19.04.2017.
 */
public class Scope {
    public static int globalMax;
    public static int globalMin;
    private int min;
    private int max;

    public Scope(int min, int max) {
        if (max > globalMax) {
            max = globalMax - 1;
        }
        if (min < globalMin) {
            min = globalMin + 1;
        }
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
