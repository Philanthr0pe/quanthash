package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;
import org.apache.commons.math3.complex.Complex;

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
            Complex image = Complex.ZERO;
            for(int i = 0; i< set.length; i++) {
                //System.out.println("x = " + j + " q = " + setSize + " b = " + set[i]);
                resultSumSin += expFunctionSin(set[i], setSize, j);
                resultSumCos += expFunctionCos(set[i], setSize, j);
                Complex multiply = new Complex(0,-1 * Math.PI * 2 * j * set[i]/q);
                //System.out.println(multiply.exp());
                image = image.add(multiply.exp());
                /*if (i == set.length / 2) {
                    double sqrt = Math.sqrt(Math.pow(resultSumCos, 2) + Math.pow(resultSumSin, 2))/set.length;
                    if (sqrt >= min) {
                        resultSumSin = 2d;
                        resultSumCos = 2d;
                        break;
                    }
                }*/
            }
            //resultSumSin /= set.length;
            //resultSumCos /= set.length;
            resultSum = Math.sqrt(Math.pow(resultSumCos, 2) + Math.pow(resultSumSin, 2));
            resultSum /= set.length;
            //System.out.println(image);
            double abs = image.abs()/set.length;
            //System.out.println(abs);
            //System.out.println(resultSum);
            if (max < abs) {
                max = abs;
                //System.out.println(image);
            }
            if (resultSum < min) {
                min = resultSum;
            }
            if(max == 0) {
                System.out.println();
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
        Double expResult = -2 * z * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.cos(expResult);
        //System.out.println("cos = " + expResult);
        return expResult;
    }

    private static Double expFunctionSin(Integer integer, Integer setSize, int z) {
        //System.out.println("x = " + z + " q = " + setSize + " b = " + integer);
        Double expResult = -2 * z * integer.intValue() * Math.PI;
        expResult /= setSize;
        expResult = Math.sin(expResult);
        //System.out.println("sin = " + expResult);
        return expResult;
    }





}
