package com.univer.quanthash.genetic.swarmOfBees;

/**
 * Created by ASUS-PC on 11.05.2017.
 */
public interface BeesAlgorithm {

    void function(int q, int d);

    BeesAlgorithm getInstance(int countOfAreas, int iterateCount);
}
