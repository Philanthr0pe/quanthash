package com.univer.quanthash.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS-PC on 19.04.2017.
 */
public class Scope {
    public static int globalMax;
    public static int globalMin;
    private int min;
    private int max;
    private int[] array;
    private int scope;

    public Scope(int[] array, int scope) {
        this.array = array;
        this.scope = scope;
    }

    public Scope(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public Set<DeltaModel> generateScopesAndStartBees(int countOfBees) {
        int minArray[] = new int[array.length];
        int maxArray[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int min = array[i] - scope;
            int max = array[i] + scope;
            min = min < globalMin ? globalMin : min;
            max = max > globalMax ? globalMax : max;
            if (max == min) {
                max++;
            }
            //System.out.println(min+ "," + max);
            if (min > max) {
                maxArray[i] = min;
                minArray[i] = max;
            } else {
                maxArray[i] = max;
                minArray[i] = min;
            }

        }
        Set<DeltaModel> deltaModels = new HashSet<>(countOfBees);
        for (int i = 0; i < countOfBees; i++) {
            deltaModels.add(new Bee(minArray, maxArray)
                            .generateDeltaModel());
        }
        return deltaModels;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
