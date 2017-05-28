package com.univer.quanthash;

import com.univer.quanthash.constructive.ConstructiveAlgorithmImpl;
import com.univer.quanthash.fullbust.FullBustAlgorithm;
import com.univer.quanthash.fullbust.FullBustAlgorithmImpl;
import com.univer.quanthash.genetic.swarmOfBees.BeesAlgorithm;
import com.univer.quanthash.genetic.swarmOfBees.BeesAlgorithmImpl;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.AdaptiveRandom;
import com.univer.quanthash.random.RandomAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;


/**
 * Created by Vladislav on 14-Apr-17.
 */
@SpringBootApplication
@PropertySource(value= {"classpath:application.properties"})
public class Application {

    @Autowired
    FullBustAlgorithm fullBustAlgorithm;

    @Value("${random}")
    String randFile;

    @Value("${bees}")
    String beesFile;

    @Value("${fullbust}")
    String fullFile;

    @Value("${randAdapt}")
    String randAdapt;

    @Value("${constr}")
    String constFile;


    RandomAlgorithm randomAlgorithm;

    BeesAlgorithm beesAlgorithm;

    ConstructiveAlgorithmImpl constructiveAlgorithm;


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            startRandomAndBees();
            //startConstr();
            //fullAlg();
        };
    }

    public void fullAlg() {
        fullBustAlgorithm = new FullBustAlgorithmImpl();
        int q = 64;
        int d = 4;
        while(q <= 128){
            d = 4;
            while (d <= 16 && d <= q/2) {
                HashSet<DeltaModel> deltaModels = fullBustAlgorithm.setOfDeltaFullBust(q, d);
                DeltaModel model = deltaModels.stream().min(DeltaModel::compareTo).get();
                System.out.println("q = " + q + " ; d = " + d);
                System.out.println(model);
                writeToFile(fullFile, q, d, model);
                d*=2;
            }
            q *= 2;
        }

    }

    public void startConstr() {
        constructiveAlgorithm = new ConstructiveAlgorithmImpl();

        int q = 8;
        double eps = 1d;

        while (q <= 17000) {
            eps = 0.75;
            while (eps > 0) {
                DeltaModel function = constructiveAlgorithm.function(q, eps);
                int size = function.getArray().length;
                int realQ = constructiveAlgorithm.getRealQ();
                writeConstrToFile(constFile, realQ, eps, size, function);
                System.out.println("q = " + realQ + " ; eps = " + eps + " ; size = " + size + "; delta = "
                        + function.getDelta());
                eps -= 0.05;
            }
            q *= 2;
        }
    }

    public void startRandomAndBees() {
        randomAlgorithm = new RandomAlgorithm();
        AdaptiveRandom adaptiveRandom = new AdaptiveRandom();
        beesAlgorithm = new BeesAlgorithmImpl();

        int q = 64;
        int d = 4;

        while (q <= 9000) {
            d = 8;
            while (d <= 1200 && d <= q / 2) {
//                DeltaModel randAlg = randAlg(q, d);
//                writeToFile(randFile, q, d, randAlg);
                DeltaModel randomDelta = adaptiveRandom.randomDelta(q, d);
                System.out.printf("q = %d, d = %d \n, %s \n", q, d, randomDelta.toString());
                writeToFile(randAdapt, q, d, randomDelta);
//                DeltaModel beesAlg = beesAlg(q, d, 0.3);
//                System.out.println("-------bees-------------");
//                writeToFile(beesFile, q, d, beesAlg);
//                System.out.println("-------randAdapt---------");
//                console(q, d, randAlg, beesAlg);
                d *= 2;
            }
            q *= 2;
        }
    }



    public void console(int q, int d, DeltaModel rand, DeltaModel bees) {
        System.out.printf("q = %d , d = %d \n", q, d);
        System.out.printf("rand : %s \n", rand.toString());
        System.out.printf("bees : %s \n", bees.toString());
    }

    public DeltaModel randAlg(int q, int d) {
        DeltaModel deltaModel = randomAlgorithm.randomDelta(q, d);
        return deltaModel;
    }
    public DeltaModel beesAlg(int q, int d, double delta) {
        DeltaModel deltaModel = beesAlgorithm.function(q, d, delta);
        return deltaModel;
    }

    public HashSet<DeltaModel> fullAlg(int q, int d) {
        HashSet<DeltaModel> deltaModels = fullBustAlgorithm.setOfDeltaFullBust(q, d);
        return deltaModels;
    }

    public boolean writeToFile(String filename, int q, int d, DeltaModel deltaModel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("q = " + q + " ; d = " + d + "\n");
            writer.write(deltaModel.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean writeConstrToFile(String filename, int q, double eps, int size, DeltaModel deltaModel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("q = " + q + " ; eps = " + eps + " ; size = " + size +  "\n");
            writer.write(deltaModel.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
