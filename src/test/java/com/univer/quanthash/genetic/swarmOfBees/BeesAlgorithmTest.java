package com.univer.quanthash.genetic.swarmOfBees;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Vladislav on 26-Apr-17.
 */
public class BeesAlgorithmTest {

    BeesAlgorithm beesAlgorithm;

    @Before
    public void setUp() throws Exception {
        beesAlgorithm = new BeesAlgorithm(10, 20);
    }

    @Test
    public void functionTest() {
        beesAlgorithm.function(8, 4);
    }

}