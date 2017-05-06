package com.univer.quanthash.fullbust;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.dao.DeltaRepository;
import com.univer.quanthash.models.DeltaModel;
import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS-PC on 06.04.2017.
 */
public class FullBustAlgorithm {

    private DeltaFunction deltaFunction;

    @Autowired
    DeltaRepository deltaRepository;

    public FullBustAlgorithm() {
        deltaFunction = new DeltaFunction();
    }

    public HashSet<DeltaModel> setOfDeltaFullBust(int q, int d) {
        HashSet<DeltaModel> hashSet = new HashSet<>();

        Set<int[]> ints = generateCombinations(q, d);
        int count = 1000;
        int count1 = 0;
        double min = 1.0;
        for (int[] anInt : ints) {
            DeltaModel deltaModel = deltaFunction.deltaFunction(anInt);
            hashSet.add(deltaModel);
            if (deltaModel.getDelta() < min) {
                System.out.println(deltaModel);
                min = deltaModel.getDelta();
            }
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
