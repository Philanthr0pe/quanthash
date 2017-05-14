package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunction {

    public static int q;

    public static DeltaModel deltaFunction(int[] set) {
        Double resultSum = 0d;
        int setSize = q;
        int i = 1;
        double max = 0.0;

        for (int j = 1; j < q ; j++) {
            resultSum = 0.0;
            for (int k = 0; k < set.length; k++) {
                resultSum += expFunction(set[k], k+1, q, j);
            }
            resultSum = resultSum/ set.length;
            resultSum = Math.abs(resultSum);
        //    System.out.println(resultSum);
            if (max < resultSum) {
                max = resultSum;
            }
        }
        return new DeltaModel(set, max);
    }

    public static Set<DeltaModel> deltaFunctionForSet(Set<int[]> ints) {
        HashSet<DeltaModel> deltaModels = new HashSet<>(ints.size());
        for (int[] anInt : ints) {
            deltaModels.add(deltaFunction(anInt));
        }
        return deltaModels;
    }

    private static Double expFunction(Integer integer, Integer number, Integer setSize, int x) {
        Double expResult = 2 * x * integer.intValue() * Math.PI;
        expResult = expResult / setSize;
        expResult = Math.cos(expResult);
        return expResult;
    }





}
