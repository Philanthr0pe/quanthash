package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunction {

    public static DeltaModel deltaFunction(int[] set) {
        Double resultSum = 0d;
        int setSize = set.length;

        for (Integer integer : set) {
            resultSum += expFunction(integer, setSize);
        }
        resultSum = resultSum/ set.length;

        return new DeltaModel(set, resultSum);
    }

    public static Set<DeltaModel> deltaFunctionForSet(Set<int[]> ints) {
        HashSet<DeltaModel> deltaModels = new HashSet<>(ints.size());
        for (int[] anInt : ints) {
            deltaModels.add(deltaFunction(anInt));
        }
        return deltaModels;
    }

    private static Double expFunction(Integer integer, Integer setSize) {
        Double expResult = (-1) * 2 * (setSize-1) * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.exp(expResult);
        return expResult;
    }




}
