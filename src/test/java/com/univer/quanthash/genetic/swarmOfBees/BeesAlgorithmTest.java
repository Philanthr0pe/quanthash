package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Vladislav on 26-Apr-17.
 */
public class BeesAlgorithmTest {

    BeesAlgorithm beesAlgorithm;

    @Before
    public void setUp() throws Exception {
        DeltaFunction.q = 8;
        beesAlgorithm = new BeesAlgorithmImpl().getInstance(1000, 1000);
    }

    @Test
    public void functionTest() {
        beesAlgorithm.function(8, 4);
    }

}