package com.univer.quanthash.fullbust;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS-PC on 06.04.2017.
 */
@Service
public class FullBustAlgorithmImpl implements FullBustAlgorithm {

    private DeltaFunction deltaFunction;

    /*@Autowired
    DeltaRepository deltaRepository;*/

    public FullBustAlgorithmImpl() {
        deltaFunction = new DeltaFunction();
    }

    public HashSet<DeltaModel> setOfDeltaFullBust(int q, int d) {
        HashSet<DeltaModel> hashSet = new HashSet<>();


        Generator<Integer> iCombinatoricsVectors = generateCombinations(q, d);
        int count = 1000;
        int count1 = 0;
        double min = 1.0;
        for (ICombinatoricsVector<Integer> anInt : iCombinatoricsVectors) {
            int[] vector = anInt.getVector().stream().mapToInt(i -> i).toArray();
            DeltaModel deltaModel = new DeltaFunction(q).deltaFunction(vector);
            if (deltaModel.getDelta() < min) {
                hashSet.add(deltaModel);
                //deltaRepository.save(deltaModel);
                System.out.println(deltaModel);
                min = deltaModel.getDelta();

            }
        }
        return hashSet;
    }

    public Generator<Integer> generateCombinations(int q, int d) {
        Integer[] integers = new Integer[q];
        for (int i = 0; i < q; i++) {
            integers[i] = i+1;
        }
        ICombinatoricsVector<Integer> vector = Factory.createVector(integers);
        Generator<Integer> multiCombinationGenerator = Factory.createMultiCombinationGenerator(vector, d);


        return multiCombinationGenerator;
        /*HashSet<int[]> intSet = new HashSet<int[]>();
        Combinations ints = new Combinations(q, d);
        for (int i = 0; i < q; i++) {
            int[] array = new int[d];
            for (int j = 0; j < d; j++) {
                array[j] = i;
            }
            intSet.add(array);
        }
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
            intSet.add(anInt);
        }
        return intSet;*/
    }

    public Set<int[]> generateCombinationsWithSize(int q, int d, int size) {

        return new HashSet<>();
    }

 /*   public long sizeOfSet(int q, int d) {
        long factorialN = CombinatoricsUtils.factorial(q + d - 1);
        long factorialD = CombinatoricsUtils.factorial(d);
        long factorialQ = CombinatoricsUtils.factorial(q - 1);
        long result = factorialN / (factorialD * factorialQ);

        return result;
    }*/
}
