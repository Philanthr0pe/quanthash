package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.Area;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.models.Scope;
import com.univer.quanthash.random.RandomAlgorithm;

import java.util.HashSet;
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
    private int iterateCount;
    private Set<List<DeltaModel>> bests;

    public BeesAlgorithm(int countOfAreas, int iterateCount) {

        startCountOfAreas = countOfAreas;
        countOfBees = 100;
        countOfBeesForBest = (int) (0.5 * countOfBees);
        countOfBeesForWorst = (int) (0.2 * countOfBees);
        countOfBestAreas = (int) (0.2 * countOfAreas);
        countOfWorstAreas = (int) (0.3 * countOfAreas);
        sizeOfArea = 1;
        deltaFunction = new DeltaFunction();
        this.iterateCount = iterateCount;
        this.bests = new HashSet<>();
    }

    public void function(int q, int d) {
        Scope.globalMin = -1;
        Scope.globalMax = q;
        Set<int[]> ints = new RandomAlgorithm().generateRandomArrs(q, d, this.startCountOfAreas);
        System.out.println(ints.size());
        Set<DeltaModel> deltaModels = deltaFunction.deltaFunctionForSet(ints);

        try {
            System.out.println(work(deltaModels));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //DeltaModel deltaModel = bests.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();
        //System.out.println(deltaModel);

    }



    public DeltaModel work(Set<DeltaModel> deltaModels) {
        List<DeltaModel> deltaModelList = deltaModels.stream().sorted().collect(Collectors.toList());
        Set<DeltaModel> deltaModelsBest = separateSetOfDeltas(deltaModelList, 0, countOfBestAreas);
        Set<DeltaModel> deltaModelsNorm = separateSetOfDeltas(deltaModelList, countOfBestAreas, countOfWorstAreas + countOfBestAreas);

        while (iterateCount-- == 0) {
            Set<DeltaModel> scopesAndGetDelta = new Area(countOfBeesForBest, deltaModelsBest, sizeOfArea)
                    .createScopesAndGetDelta();
            scopesAndGetDelta.addAll(new Area(countOfBeesForWorst, deltaModelsNorm, sizeOfArea)
                    .createScopesAndGetDelta());
            deltaModelList = scopesAndGetDelta.stream().sorted().collect(Collectors.toList());
            deltaModelsBest = separateSetOfDeltas(deltaModelList,
                    0,
                    countOfBestAreas);
            deltaModelsNorm = separateSetOfDeltas(deltaModelList,
                    countOfBestAreas,
                    countOfWorstAreas + countOfBestAreas);
        }
        System.out.println(deltaModelList.get(0));
        System.out.println(deltaModelList.get(1));

        return deltaModelList.get(0);
    }

    private Set<DeltaModel> separateSetOfDeltas(List<DeltaModel> deltaModels, int start, int end) {
        Set<DeltaModel> result = new HashSet<>();
        for (int i = start; i < end; i++) {
            result.add(deltaModels.get(i));
        }
        return result;
    }

}