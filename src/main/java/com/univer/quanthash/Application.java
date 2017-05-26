package com.univer.quanthash;

import com.univer.quanthash.dao.DeltaRepository;
import com.univer.quanthash.fullbust.FullBustAlgorithm;
import com.univer.quanthash.genetic.swarmOfBees.BeesAlgorithm;
import com.univer.quanthash.genetic.swarmOfBees.BeesAlgorithmImpl;
import com.univer.quanthash.models.DeltaModel;
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


    RandomAlgorithm randomAlgorithm;

    BeesAlgorithm beesAlgorithm;


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(DeltaRepository repository) {
        return (args) -> {
            randomAlgorithm = new RandomAlgorithm();
            beesAlgorithm = new BeesAlgorithmImpl(500, 1000);

            int q = 8;
            int d = 4;

            while (q <= 256) {
                d = 4;
                while (d <= 16 && d <= q / 2) {
                    /*DeltaModel randAlg = randAlg(q, d);
                    writeToFile(randFile, q, d, randAlg);
                    DeltaModel beesAlg = beesAlg(q, d, randAlg.getDelta());
                    writeToFile(beesFile, q, d, beesAlg);
                    console(q, d, randAlg, beesAlg);*/
                    DeltaModel fullAlg = fullAlg(q, d).stream().min(DeltaModel::compareTo).get();
                    writeToFile(fullFile, q, d, fullAlg);
                    console(q, d, fullAlg);
                    d *= 2;
                }
                q *= 2;
            }
        };
    }
    public void console(int q, int d, DeltaModel full) {
        System.out.printf("q = %d , d = %d \n", q, d);
        /*System.out.printf("rand : %s \n", rand.toString());
        System.out.printf("bees : %s \n", bees.toString());*/
        System.out.println(full);
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



}
