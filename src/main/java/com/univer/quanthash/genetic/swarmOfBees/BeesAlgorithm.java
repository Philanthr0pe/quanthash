package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.Utils.ScopeListUtils;
import com.univer.quanthash.Utils.ScopeUtils;
import com.univer.quanthash.models.Area;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.models.Scope;
import com.univer.quanthash.random.RandomAlgorithm;

import java.util.*;
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
    private Set<DeltaModel> bests;

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

        List<DeltaModel> collectOfDeltaModels = deltaModels.stream()
                .sorted()
                .collect(Collectors.toList());

        try {
            System.out.println(work(collectOfDeltaModels));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //DeltaModel deltaModel = bests.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();
        //System.out.println(deltaModel);

    }

    public DeltaModel work(List<DeltaModel> deltaModels) {
        //Collections.sort(deltaModels);
        List<DeltaModel> collect = deltaModels;
        while (iterateCount != 0 || collect.size() == 0) {
            deltaModels = collect;
            List<Area> areas = areaByModels(deltaModels, countOfBestAreas, countOfWorstAreas);
            Set<int[]> ints = arrayByAreas(areas);
            Set<DeltaModel> deltaModels1 = deltaFunction.deltaFunctionForSet(ints);
            collect = deltaModels1.stream()
                    .sorted()
                    .collect(Collectors.toList());
            iterateCount--;
        }
        System.out.println(deltaModels.get(0));
        return deltaModels.get(0);
    }

    public List<Area> areaByModels(List<DeltaModel> models, int best, int others) {
        ArrayList<Area> areas = new ArrayList<>();
        for (int i = 0; i < best; i++) {
            areas.add(new Area(countOfBeesForBest, models.get(i)));
        }
        for (int i = best; i < others + best; i++) {
            areas.add(new Area(countOfBeesForWorst, models.get(i)));
        }

        return areas;
    }

    public Set<int[]> arrayByAreas(List<Area> areas) {
        HashSet<int[]> result = new HashSet<>();
        for (Area area : areas) {
            result.addAll(arrayByDeltaModel(area.getDeltaModel(), area.getCountOfBees()));
        }
        return result;
    }

    public Set<int[]> arrayByDeltaModel(DeltaModel deltaModel, int countOfBees) {
        Set<int[]> ints = new ScopeListUtils(
                new ScopeUtils(deltaModel, sizeOfArea).generateScopeList())
                .generateArrsByScope(countOfBees);
        return ints;
    }



}