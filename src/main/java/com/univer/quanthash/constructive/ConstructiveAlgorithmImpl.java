package com.univer.quanthash.constructive;

import com.univer.quanthash.models.DeltaModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * Created by ASUS-PC on 14.05.2017.
 */
public class ConstructiveAlgorithmImpl {

    public DeltaModel function(int q, int d, double eps) {

        DeltaModel result = null;


        return result;
    }

    private List<Integer> generatePrimeSet(int q, double eps) {
        ArrayList<Integer> integers = new ArrayList<>();
        double min = pow(log(q), 1 + eps) / 2;
        double max = pow(log(q), 1 + eps);
        int i = (int) min + 1;
        while (i < max) {
            integers.add(i++);
        }
        return integers;
    }

    private List<Integer> generateSSet(int q, double eps) {
        ArrayList<Integer> integers = new ArrayList<>();
        int max = (int) pow(log(q), 1 + 2 * eps);
        while (max !=0) {
            integers.add(max--);
        }
        return integers;
    }




}
