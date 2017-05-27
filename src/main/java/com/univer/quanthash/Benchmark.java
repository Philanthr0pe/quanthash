package com.univer.quanthash;

import com.univer.quanthash.constructive.ConstructiveAlgorithmImpl;
import com.univer.quanthash.genetic.swarmOfBees.BeesAlgorithmImpl;
import com.univer.quanthash.models.DeltaModel;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static com.univer.quanthash.DeltaFunction.q;

/**
 * Created by ASUS-PC on 26.05.2017.
 */

@State(Scope.Benchmark)
public class Benchmark {

    @Param({"8", "16", "32", "64", "128"})
    public int a;

    @Param({"4"})
    public int b;


    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(5)
    public DeltaModel measureBees() {
        DeltaModel function = new BeesAlgorithmImpl().function(a, b, 0.5);
        return function;
    }

    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(5)
    public DeltaModel measureConstr() {
        DeltaModel function = new ConstructiveAlgorithmImpl().function(q, 0.5);
        return function;
    }



    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
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
