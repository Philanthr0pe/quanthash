package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunction {

    public static int q;
    private static double min = 10d;

    public DeltaFunction(int q) {
        this.q = q;
    }

    public DeltaFunction() {}

    public static DeltaModel deltaFunction(int[] set) {
        Double resultSumSin = 0d;
        Double resultSumCos = 0d;
        Double resultSum;
        int setSize = q;
        double max = 0.0;

        for (int j = 1; j < q ; j++) {
            resultSumSin = 0d;
            resultSumCos = 0d;
            for(int i = 0; i< set.length; i++) {
                resultSumSin += expFunctionSin(set[i], setSize, j);
                resultSumCos += expFunctionCos(set[i], setSize, j);
                if (i >= set.length / 2) {
                    double sqrt = Math.sqrt(Math.pow(resultSumCos, 2) + Math.pow(resultSumSin, 2))/set.length;
                    if (sqrt >= min) {
                        resultSumSin = 2d;
                        resultSumCos = 2d;
                        break;
                    }
                }
            }
            //resultSumSin /= set.length;
            //resultSumCos /= set.length;
            resultSum = Math.sqrt(Math.pow(resultSumCos, 2) + Math.pow(resultSumSin, 2));
            resultSum /= set.length;
            //System.out.println(resultSum);
            if (max < resultSum) {
                max = resultSum;
            }
            if (resultSum < min) {
                min = resultSum;
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

    private static Double expFunctionCos(Integer integer, Integer setSize, int z) {
        Double expResult = 2 * z * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.cos(expResult);
        return expResult;
    }

    private static Double expFunctionSin(Integer integer, Integer setSize, int z) {
        Double expResult = 2 * z * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = -Math.sin(expResult);
        return expResult;
    }





}
