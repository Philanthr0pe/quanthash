package com.univer.quanthash.models;

/**
 * Created by ASUS-PC on 19.04.2017.
 */
public class Scope {
    private int min;
    private int max;

    public Scope(int min, int max) {
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
