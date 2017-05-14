package com.univer.quanthash.genetic.swarmOfBees;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Vladislav on 26-Apr-17.
 */
public class BeesAlgorithmTest {

    BeesAlgorithm beesAlgorithmImpl;

    @Before
    public void setUp() throws Exception {
        beesAlgorithmImpl = new BeesAlgorithmImpl().getInstance(100, 100);
    }

    @Test
    public void functionTest() {
        beesAlgorithmImpl.function(8, 4);
    }

}