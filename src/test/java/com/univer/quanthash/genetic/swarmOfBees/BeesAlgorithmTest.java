package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Vladislav on 26-Apr-17.
 */
public class BeesAlgorithmTest {

    @Autowired
    BeesAlgorithm beesAlgorithm;

    @Before
    public void setUp() throws Exception {
        DeltaFunction.q = 8;
        beesAlgorithm = new BeesAlgorithmImpl().getInstance(1000, 1000);
    }

    @Test
    public void functionTest() {
        int q = 4;
        for (int i = 0; i < 4; i++) {
            q *= 2;
            DeltaModel deltaModel = new BeesAlgorithmImpl(500, 200)
                    .function(q, 4, 0.5);
            System.out.println(q + " " + 4);
            System.out.println(deltaModel);
        }
        q = 8;
        for (int i = 0; i < 4; i++) {
            q *=2;
            DeltaModel deltaModel = new BeesAlgorithmImpl(500 , 200)
                    .function(q, 8, 0.5);
            System.out.println(q + " " + 8);
            System.out.println(deltaModel);
        }
    }

}