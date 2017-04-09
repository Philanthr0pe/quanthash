package com.univer.quanthash.fullbust;

import org.junit.Before;
import org.junit.Test;

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
    public void deltaFullBust() throws Exception {
        double notExpected = 0;
        int q = 16;
        int d = 2;
        HashSet<Integer> integers = new HashSet<>();
        double delta = fullBustAlgorithm.deltaFullBust(q, d, integers);

        assertNotEquals(notExpected, delta);
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
        Set<Set<Integer>> sets = fullBustAlgorithm.setOfDeltaFullBust(q, d);

        assertNotNull(sets);
    }

}