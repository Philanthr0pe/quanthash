package com.univer.quanthash;

import com.univer.quanthash.dao.DeltaRepository;
import com.univer.quanthash.fullbust.FullBustAlgorithm;
import com.univer.quanthash.genetic.swarmOfBees.BeesAlgorithm;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.RandomAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Vladislav on 14-Apr-17.
 */
@SpringBootApplication
public class Application {


    @Autowired
    DeltaRepository repository;

    @Autowired
    FullBustAlgorithm fullBustAlgorithm;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            DeltaFunction.q = 16;

            RandomAlgorithm randomAlgorithm = new RandomAlgorithm(1000);
            Set<DeltaModel> deltaModelsRand = randomAlgorithm.randomDelta(32, 16);
            DeltaModel minDeltaModel = deltaModelsRand.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();
            repository.save(deltaModelsRand);
            System.out.println("minRand: " + minDeltaModel);

            new BeesAlgorithm(10000, 10000).function(32, 16);

            HashSet<DeltaModel> deltaModelsFull = fullBustAlgorithm.setOfDeltaFullBust(32, 16);


            DeltaModel deltaModel1 = deltaModelsFull.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();


            repository.save(deltaModelsFull);

            System.out.println("minFull: " + deltaModel1);
        };
    }


}
