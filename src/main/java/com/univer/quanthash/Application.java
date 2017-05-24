package com.univer.quanthash;

import com.univer.quanthash.dao.DeltaRepository;
import com.univer.quanthash.fullbust.FullBustAlgorithm;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.RandomAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Set;


/**
 * Created by Vladislav on 14-Apr-17.
 */
@SpringBootApplication
public class Application {

    @Autowired
    FullBustAlgorithm fullBustAlgorithm;


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(DeltaRepository repository) {
        return (args) -> {
            RandomAlgorithm randomAlgorithm = new RandomAlgorithm();


            int q = 8;
            int d = 4;

            while (q < 128) {
                d = 8;
                while (d <= 16 && d <= q / 2) {
                    Set<DeltaModel> deltaModels = fullBustAlgorithm.setOfDeltaFullBust(q, d);
                    System.out.println("q = " + q + " ; d = " + d);
                    DeltaModel minDelta = deltaModels.stream().min(DeltaModel::compareTo).get();
                    System.out.println(minDelta);
                    try (BufferedWriter writer = new BufferedWriter(
                            new FileWriter("D:\\Vlad\\quanthash\\src\\main\\resources\\fullbust.txt", true))) {
                        writer.write("q = " + q + " ; d = " + d + "\n");
                        writer.write(minDelta.toString());
                        writer.write("\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                    d *= 2;
                }
                q *= 2;
            }
        };
    }




}
