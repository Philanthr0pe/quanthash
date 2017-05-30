package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.AdaptiveRandom;
import com.univer.quanthash.random.RandomAlgorithm;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by ASUS-PC on 26.05.2017.
 */

@State(Scope.Benchmark)
public class Benchmark {

    @Param("8")
    public int a;

    @Param("4")
    public int b;

    private double delta = 0d;


    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public DeltaModel measureAdapt() {
        //System.out.println(a + "  " + b);
        DeltaModel function = new AdaptiveRandom().randomDelta(a, b);
        delta = function.getDelta();
        return function;
    }

    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public DeltaModel measureRand() {
        DeltaModel function = new RandomAlgorithm().randomDelta(a, b);
        return function;
    }

//    @org.openjdk.jmh.annotations.Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    @Fork(5)
//    public DeltaModel measureBees() {
//        DeltaModel function = new BeesAlgorithmImpl().function(a, b, delta);
//        return function;
//    }

//    @org.openjdk.jmh.annotations.Benchmark
//    @BenchmarkMode(Mode.All)
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    @Fork(5)
//    public DeltaModel measureConstr() {
//        DeltaModel function = new ConstructiveAlgorithmImpl().function();
//        return function;
//    }



    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .param("a", "128", "256")
                .param("b", "8", "16", "32", "64", "128")
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

}
