package com.univer.quanthash.fullbust;

import com.univer.quanthash.DeltaFunction;
import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS-PC on 06.04.2017.
 */
public class FullBustAlgorithm {

    private DeltaFunction deltaFunction;

    public FullBustAlgorithm() {
        deltaFunction = new DeltaFunction();
    }

    public Set<Set<Integer>> setOfDeltaFullBust(int q, int d) {
        HashSet<Set<Integer>> hashSets = new HashSet<>();
        long sizeOfSet = sizeOfSet(q, d);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < d; i++) {
            list.add(i);
        }

        Combinations ints = new Combinations(q, d);

        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return hashSets;
    }

    public double deltaFullBust(int q, int d, Set<Integer> integers) {
        int size;
        return deltaFunction.deltaFunction(integers);
    }

    public long sizeOfSet(int q, int d) {
        long factorialN = CombinatoricsUtils.factorial(q + d - 1);
        long factorialD = CombinatoricsUtils.factorial(d);
        long factorialQ = CombinatoricsUtils.factorial(q - 1);
        long result = factorialN / (factorialD * factorialQ);

        return result;
    }

    public Set<Integer> generateSet(int d) {
        HashSet<Integer> result = new HashSet<>(d);

        return result;
    }


}
