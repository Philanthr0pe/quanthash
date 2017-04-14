package com.univer.quanthash;

import com.univer.quanthash.dao.DeltaRepository;
import com.univer.quanthash.fullbust.FullBustAlgorithm;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.RandomAlgorithm;
import org.hibernate.boot.archive.scan.spi.Scanner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 14-Apr-17.
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(DeltaRepository repository) {
        return (args) -> {

            RandomAlgorithm randomAlgorithm = new RandomAlgorithm(100);
            randomAlgorithm.

        };
    }



}
