package com.univer.quanthash.models;

/**
 * Created by Vladislav on 10-Apr-17.
 */
public class DeltaModel {
    int[] array;
    double delta;

    public DeltaModel(int[] array, double delta) {
        this.array = array;
        this.delta = delta;
    }

    public int[] getArray() {
        return array;
    }

    public double getDelta() {
        return delta;
    }
}
