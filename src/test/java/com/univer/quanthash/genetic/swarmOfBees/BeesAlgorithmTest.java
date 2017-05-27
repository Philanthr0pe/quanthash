package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vladislav on 26-Apr-17.
 */
public class BeesAlgorithmTest {


    @Before
    public void setUp() throws Exception {
        DeltaFunction.q = 8;
        BeesAlgorithm beesAlgorithm = new BeesAlgorithmImpl().getInstance(1000, 1000);
    }

    @Test
    public void functionTest() {
        int q = 32;
        int d = 4;
        while (q <= 512) {
            d = 4;
            while (d <= 32 && d <= q / 2) {
                DeltaModel deltaModel = new BeesAlgorithmImpl()
                        .function(q, d, 0.5);
                System.out.println(q + " " + d);
                System.out.println(deltaModel);
                d *= 2;
            }
            q *= 2;

        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(5)
    public void measureName() {
        new BeesAlgorithmImpl(500, 200).function(8, 4, 0.5);
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(BeesAlgorithmTest.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}