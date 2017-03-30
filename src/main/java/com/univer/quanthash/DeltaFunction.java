package com.univer.quanthash;

import java.util.List;

/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunction {

    public static Double deltaFunction(List<Integer> list, Integer setSize) {
        Double resultSum = 0d;

        for (Integer integer : list) {
            resultSum += expFunction(integer, setSize);
        }
        resultSum = resultSum/list.size();

        return resultSum;
    }

    private static Double expFunction(Integer integer, Integer setSize) {
        Double expResult = 2 * (setSize-1) * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.exp(expResult);
        return expResult;
    }




}
