package com.univer.quanthash.random;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;

import java.util.Random;

/**
 * Created by ASUS-PC on 27.05.2017.
 */
public class AdaptiveRandom {

    private int scope;
    private int step;

    public DeltaModel randomDelta(int q, int d) {
        scope = 0;
        step = (int)(Math.log(q)/Math.log(2));
        int size = (int)Math.pow(Math.log(q * d) / Math.log(2), 2) * 10;
        return randomDeltaByIter(q, d, size);
    }

    public DeltaModel randomDeltaByIter(int q, int d, int iterCount) {
        DeltaModel tempDelta = getRandomDelta(q, d);
        DeltaModel bigTemp;
        DeltaModel minDelta = new DeltaModel(tempDelta.getArray(), tempDelta.getDelta());
        for (int i = 0; i < iterCount; i++) {
            tempDelta = getNewPointByOld(q, d, tempDelta);
            bigTemp = getNewPointByOld(q, d, tempDelta);
            if (tempDelta.compareTo(bigTemp) <= 0) {
                scope -= step;
            } else {
                tempDelta = bigTemp;
            }
            if (minDelta.compareTo(tempDelta) >= 0) {
                minDelta = new DeltaModel(tempDelta.getArray(), tempDelta.getDelta());
                //System.out.println("min = " + minDelta);
            }
            if (scope >= q-1) {
                scope = q/2;
                iterCount *= 0.5;
            }
        }
        return minDelta;
    }

    public DeltaModel getNewPointByOld(int q, int d, DeltaModel deltaModel) {
        int[] array = deltaModel.getArray();
        scope += step;
        int[] result = new int[d];
        for (int i = 0; i < d; i++) {
            int min = array[i] - scope;
            int max = array[i] + scope;
            min = min < 0 ? 0 : min;
            max = max > q-1 ? q-1 : max;
            result[i] = new Random().ints(min, max).findFirst().getAsInt();
        }
        return new DeltaFunction(q).deltaFunction(result);
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
