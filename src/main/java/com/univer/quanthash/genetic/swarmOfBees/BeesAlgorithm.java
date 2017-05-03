package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.RandomAlgorithm;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 14-Apr-17.
 */
public class BeesAlgorithm {

    private int startCountOfAreas;
    private int countOfBees;
    private int countOfBeesForBest;
    private int countOfBeesForWorst;
    private int countOfBestAreas;
    private int countOfWorstAreas;
    private int sizeOfArea;
    private DeltaFunction deltaFunction;

    public BeesAlgorithm(int countOfAreas) {
        startCountOfAreas = countOfAreas;
        countOfBees = 10;
        countOfBeesForBest = 5;
        countOfBeesForWorst = 2;
        countOfBestAreas = 2;
        countOfWorstAreas = 3;
        sizeOfArea = 10;
        deltaFunction = new DeltaFunction();
    }

    public void function(int q, int d) {
        Set<int[]> ints = new RandomAlgorithm().generateRandomArrs(q, d, this.startCountOfAreas);
        System.out.println(ints.size());
        Set<DeltaModel> deltaModels = deltaFunction.deltaFunctionForSet(ints);

        List<DeltaModel> collectOfDeltaModels = deltaModels.stream()
                .sorted((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta()))
                .collect(Collectors.toList());

        System.out.println("------------------------------------");
        for (DeltaModel collectOfDeltaModel : collectOfDeltaModels) {
            System.out.println(collectOfDeltaModel);
        }
    }


}