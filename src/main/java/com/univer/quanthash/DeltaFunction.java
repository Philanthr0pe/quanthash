package com.univer.quanthash;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunction {

    public static Double deltaFunction(int[] set) {
        Double resultSum = 0d;
        int setSize = set.length;

        for (Integer integer : set) {
            resultSum += expFunction(integer, setSize);
        }
        resultSum = resultSum/ set.length;

        return resultSum;
    }

    public static Set<Double> deltaFunctionForSet(Set<int[]> ints) {
        HashSet<Double> doubles = new HashSet<>(ints.size());
        for (int[] anInt : ints) {
            doubles.add(deltaFunction(anInt));
        }
        return doubles;
    }

    private static Double expFunction(Integer integer, Integer setSize) {
        Double expResult = (-integer.intValue()) * 2 * (setSize-1) * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.exp(expResult);
        return expResult;
    }




}
