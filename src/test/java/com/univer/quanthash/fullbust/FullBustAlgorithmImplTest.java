package com.univer.quanthash.fullbust;

import com.univer.quanthash.models.DeltaModel;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Before;
import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


/**
 * Created by ASUS-PC on 06.04.2017.
 */
public class FullBustAlgorithmImplTest {

    FullBustAlgorithmImpl fullBustAlgorithmImpl;

    @Before
    public void setUp() throws Exception {
        fullBustAlgorithmImpl = new FullBustAlgorithmImpl();
    }

    @Test
    public void setSize() throws Exception {
        int q = 4;
        int d = 2;
        long l = 0;
        System.out.println(l);
        assertEquals(10, l);


    }

    @Test
    public void setOfDeltaFullBust() {
        int q = 2;
        int d = 2;
        Set<DeltaModel> sets1 = fullBustAlgorithmImpl.setOfDeltaFullBust(q, d);
        Set<int[]> collect = sets1.stream().map(del -> del.getArray()).collect(Collectors.toSet());
    }

    @Test
    public void generateCombinations() {
        CombinatoricsUtils.factorial(20);

        int q = 4;
        int d = 4;
        /*Set<int[]> ints = null;
        Set<List<Integer>> intsInt = ints.stream()
                .map(i -> IntStream.of(i).boxed().collect(Collectors.toList()))
                .collect(Collectors.toSet());
        System.out.println("size = " + ints.size());
        HashSet<List<Integer>> collect = new HashSet<List<Integer>>();

        final int plus = q;

        for (List<Integer> set : intsInt) {
            System.out.println(Arrays.toString(set.toArray()));
            List<Integer> intArr = set.stream().map(i -> i += plus).collect(Collectors.toList());
            collect.add(intArr);
            for (int i = 0; i < k; i++) {
                ArrayList<Integer> clone = new ArrayList<>(set);
                clone.set(i, set.get(i) + plus);
                clone.sort(Integer::compareTo);
                collect.add(clone);
            }
        }
        intsInt.addAll(collect);
        //ints = ints.stream().peek(arr -> Arrays.sort(arr)).distinct().collect(Collectors.toSet());
        System.out.println("size = " + intsInt.size());
        intsInt.forEach(c -> System.out.println(Arrays.toString(c.toArray())));
        System.out.println("-------------------");

        q *= 2;
        Set<int[]> ints1 = null;
        Set<List<Integer>> collect1 = ints1.stream()
                .map(i -> IntStream.of(i).boxed().collect(Collectors.toList()))
                .collect(Collectors.toSet());

        collect.forEach(System.out::println);

        System.out.println("size = " + ints1.size());
        System.out.println(collect1.equals(intsInt));
        */
        Integer[] integers1 = new Integer[32];
        for (int i = 0; i < 32; i++) {
            integers1[i] = i+1;
        }
        ICombinatoricsVector<Integer> vector = Factory.createVector(integers1);
        Generator<Integer> multiCombinationGenerator = Factory.createMultiCombinationGenerator(vector, 16);
        /*for (ICombinatoricsVector<Integer> integers : multiCombinationGenerator) {
            for (Integer integer : integers.getVector()) {
                System.out.println(integer);
            }
        }*/
        //assertEquals(collect1, intsInt);


    }

}