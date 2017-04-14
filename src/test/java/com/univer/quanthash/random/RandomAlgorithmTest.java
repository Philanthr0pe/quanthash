package com.univer.quanthash.random;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

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

        Set<DeltaModel> doubles = randomAlgorithm.randomDelta(q, d);
        for (DeltaModel aDouble : doubles) {
            System.out.println(aDouble);
        }

        assertNotNull(doubles);

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