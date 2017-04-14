package com.univer.quanthash.fullbust;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
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

    public HashSet<DeltaModel> setOfDeltaFullBust(int q, int d) {
        HashSet<DeltaModel> hashSet = new HashSet<>();

        Set<int[]> ints = generateCombinations(q, d);
        for (int[] anInt : ints) {
            hashSet.add(deltaFunction.deltaFunction(anInt));
        }

        return hashSet;
    }

    public Set<int[]> generateCombinations(int q, int d) {
        HashSet<int[]> intSet = new HashSet<int[]>();

        Combinations ints = new Combinations(q, d);
        for (int i = 0; i < q; i++) {
            int[] array = new int[d];
            for (int j = 0; j < d; j++) {
                array[j] = i;
            }
            intSet.add(array);
        }
        for (int[] anInt : ints) {
            intSet.add(anInt);
        }
        return intSet;
    }

    public long sizeOfSet(int q, int d) {
        long factorialN = CombinatoricsUtils.factorial(q + d - 1);
        long factorialD = CombinatoricsUtils.factorial(d);
        long factorialQ = CombinatoricsUtils.factorial(q - 1);
        long result = factorialN / (factorialD * factorialQ);

        return result;
    }
}
