package com.univer.quanthash.fullbust;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by ASUS-PC on 06.04.2017.
 */
public class FullBustAlgorithmTest {

    FullBustAlgorithm fullBustAlgorithm;

    @Before
    public void setUp() throws Exception {
        fullBustAlgorithm = new FullBustAlgorithm();
    }

    @Test
    public void setSize() throws Exception {
        int q = 4;
        int d = 2;
        long l = fullBustAlgorithm.sizeOfSet(q, d);
        System.out.println(l);
        assertEquals(10, l);


    }

    @Test
    public void setOfDeltaFullBust() {
        int q = 4;
        int d = 2;
        Set<Double> sets = fullBustAlgorithm.setOfDeltaFullBust(q, d);

        for (Double set : sets) {
            System.out.println(set);
        }

        assertNotNull(sets);
    }

    @Test
    public void generateCombinations() {
        int q = 4;
        int d = 2;
        Set<int[]> ints = fullBustAlgorithm.generateCombinations(q, d);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
        assertNotNull(ints);
    }

}