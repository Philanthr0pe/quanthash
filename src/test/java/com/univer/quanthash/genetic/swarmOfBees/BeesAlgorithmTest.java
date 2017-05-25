package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vladislav on 26-Apr-17.
 */
public class BeesAlgorithmTest {

    //BeesAlgorithm beesAlgorithm


    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureName() {
        DeltaModel deltaModel = new BeesAlgorithmImpl().getInstance(1000, 2000)
                .function(8, 4);
        System.out.println(deltaModel);
    }

    @Before
    public void setUp() throws Exception {
        DeltaFunction.q = 8;
        new BeesAlgorithmImpl().getInstance(1000, 1000);
    }

    @Test
    public void functionTest() {
        for (int i = 0; i < 5; i++) {
            int q = 8;
            DeltaModel deltaModel = new BeesAlgorithmImpl(1000, 1000)
                    .function(q, 4);
            System.out.println(deltaModel);
        }
    }

}