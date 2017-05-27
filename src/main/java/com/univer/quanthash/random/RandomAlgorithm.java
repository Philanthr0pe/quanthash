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
    int max = 5000000;

    public RandomAlgorithm() {
        deltaFunction = new DeltaFunction();
    }

    public DeltaModel randomDelta(int q, int d) {
        size = sizeOfSet(q, d);
        Set<int[]> ints = generateRandomArrs(q, d, size);
        deltaFunction = new DeltaFunction(q);
        DeltaModel resultModel = null;
        double min = 10d;
        for (int[] anInt : ints) {
            DeltaModel deltaModel = deltaFunction.deltaFunction(anInt);
            if (min > deltaModel.getDelta()) {
                resultModel = deltaModel;
            }
        }
        return resultModel;
    }



    public int sizeOfSet(int q, int d) {
        int f = q*d;
        if (f > max) {
            return max;
        }
        return f;
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
