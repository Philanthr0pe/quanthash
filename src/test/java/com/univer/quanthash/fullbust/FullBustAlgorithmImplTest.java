package com.univer.quanthash.fullbust;

import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
        long l = fullBustAlgorithmImpl.sizeOfSet(q, d);
        System.out.println(l);
        assertEquals(10, l);


    }

    @Test
    public void setOfDeltaFullBust() {
        int q = 4;
        int d = 2;
        Set<DeltaModel> sets = fullBustAlgorithmImpl.setOfDeltaFullBust(q, d);

        for (DeltaModel set : sets) {
            System.out.println(set);
        }

        assertNotNull(sets);
    }

    @Test
    public void generateCombinations() {
        int q = 128;
        int d = 16;
        Set<int[]> ints = fullBustAlgorithmImpl.generateCombinations(q, d);
        System.out.println("size = " + ints.size());
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
        assertNotNull(ints);
    }

}