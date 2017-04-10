package com.univer.quanthash.random;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Vladislav on 10-Apr-17.
 */
public class RandomAlgorithm {

    DeltaFunction deltaFunction;
    int size;

    public RandomAlgorithm(int size) {
        this.size = size;
        deltaFunction = new DeltaFunction();
    }

    public RandomAlgorithm() {
        size = 100;
        deltaFunction = new DeltaFunction();
    }

    public Set<Double> randomDelta(int q, int d) {
        HashSet<DeltaModel> result = new HashSet<>();

        Set<int[]> ints = generateRandomArrs(q, d, size);
        Set<Double> doubles = deltaFunction.deltaFunctionForSet(ints);

        return doubles;
    }

    public Set<int[]> generateRandomArrs(int q, int d, int size) {
        HashSet<int[]> ints = new HashSet<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int[] array = new int[d];
            for (int j = 0; j < d; j++) {
                array[j] =  random.nextInt(q);
            }
            ints.add(array);
        }
        return ints;
    }
}
