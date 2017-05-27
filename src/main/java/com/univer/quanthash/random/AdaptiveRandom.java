package com.univer.quanthash.random;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;

import java.util.Random;

/**
 * Created by ASUS-PC on 27.05.2017.
 */
public class AdaptiveRandom {

    public DeltaModel randomDelta(int q, int d) {
        int size = (int)Math.pow(Math.log(q * d) / Math.log(2), 2);
        return randomDeltaByIter(q, d, size);
    }

    public DeltaModel randomDeltaByIter(int q, int d, int iterCount) {
        DeltaModel tempDelta = getRandomDelta(q, d);
        DeltaModel minDelta = new DeltaModel(tempDelta.getArray(), tempDelta.getDelta());
        for (int i = 0; i < iterCount; i++) {
            tempDelta = getNewPointByOld(q, d, tempDelta);
            if (minDelta.compareTo(tempDelta) < 0) {
                minDelta = new DeltaModel(tempDelta.getArray(), tempDelta.getDelta());
            }
        }
        return minDelta;
    }

    public DeltaModel getNewPointByOld(int q, int d, DeltaModel deltaModel) {
        int[] array = deltaModel.getArray();
        int scope = (int)(Math.log(q)/Math.log(2));
        int[] result = new int[d];
        for (int i = 0; i < d; i++) {
            int min = array[i] - scope;
            int max = array[i] + scope;
            min = min < 0 ? 0 : min;
            max = max > q-1 ? q-1 : max;
            result[i] = new Random().ints(min, max).findFirst().getAsInt();
        }
        return new DeltaFunction(q).deltaFunction(array);
    }




    private DeltaModel getRandomDelta(int q, int d) {
        Random random = new Random();
        int[] array = new int[d];
        for (int j = 0; j < d; j++) {
            array[j] =  random.nextInt(q);
        }
        DeltaModel deltaModel = new DeltaFunction(q).deltaFunction(array);
        return deltaModel;
    }
}
