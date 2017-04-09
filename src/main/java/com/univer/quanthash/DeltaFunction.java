package com.univer.quanthash;

import java.util.Set;
/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunction {

    public static Double deltaFunction(Set<Integer> set) {
        Double resultSum = 0d;
        int setSize = set.size();



        for (Integer integer : set) {
            resultSum += expFunction(integer, setSize);
        }
        resultSum = resultSum/ set.size();

        return resultSum;
    }

    private static Double expFunction(Integer integer, Integer setSize) {
        Double expResult = 2 * (setSize-1) * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.exp(expResult);
        return expResult;
    }




}
