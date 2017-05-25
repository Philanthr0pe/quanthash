package com.univer.quanthash.models;

import com.univer.quanthash.DeltaFunction;

import java.util.Random;

/**
 * Created by ASUS-PC on 04.05.2017.
 */
public class Bee {
    private int[] maxArray;
    private int[] minArray;
    private int q;

    public Bee(int[] minArray, int[] maxArray, int q) {
        this.maxArray = maxArray;
        this.minArray = minArray;
        this.q = q;
    }

    public DeltaModel generateDeltaModel() {
        int[] array = new int[minArray.length];
        for (int i = 0; i < minArray.length; i++) {
            array[i] = new Random().ints(minArray[i], maxArray[i]).findFirst().getAsInt();
        }
        //System.out.println("----------");
        return countDelta(array);
    }

    private DeltaModel countDelta (int[] array) {
        return new DeltaFunction(q).deltaFunction(array);
    }
}
