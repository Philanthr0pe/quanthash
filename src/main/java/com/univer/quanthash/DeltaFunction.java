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
        double max = 0.0;

        for (int j = 1; j < q ; j++) {
            Complex image = Complex.ZERO;
            for(int i = 0; i< set.length; i++) {
                Complex multiply = new Complex(0,-1 * Math.PI * 2 * j * set[i]/q);
                image = image.add(multiply.exp());
            }
            double abs = image.abs()/set.length;
            if (max < abs) {
                max = abs;
            }
            if (abs < min) {
                min = abs;
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
