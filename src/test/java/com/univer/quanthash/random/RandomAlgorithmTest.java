package com.univer.quanthash.random;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Vladislav on 10-Apr-17.
 */
public class RandomAlgorithmTest {

    RandomAlgorithm randomAlgorithm;

    @Before
    public void prepare() {
        randomAlgorithm = new RandomAlgorithm();
    }

    @Test
    public void randomDelta() throws Exception {
        int q = 4;
        int d = 2;

    }



    @Test
    public void sizeOfSet() throws Exception {
        int f = 20;
        long factorialN = CombinatoricsUtils.factorial(f);
        long factorialD = CombinatoricsUtils.factorial(4);
        long factorialQ = CombinatoricsUtils.factorial(15);
        long result = factorialN / (factorialD * factorialQ);
        result /= 10;
        System.out.println(result);
    }

    @Test
    public void generateRandomArrs() throws Exception {
        int q = 4;
        int d = 2;
        int size = 100;

        Set<int[]> ints = randomAlgorithm.generateRandomArrs(q, d, size);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }

        assertNotNull(ints);
    }

}