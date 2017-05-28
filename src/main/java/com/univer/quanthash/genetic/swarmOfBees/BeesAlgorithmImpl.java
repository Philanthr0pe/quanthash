package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.Area;
import com.univer.quanthash.models.Bee;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.models.Scope;
import com.univer.quanthash.random.RandomAlgorithm;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 14-Apr-17.
 */
@Service
public class BeesAlgorithmImpl implements BeesAlgorithm {

    /*@Autowired
    DeltaRepository deltaRepository;*/

    private int startCountOfAreas;
    public int q;
    public int d;
    private int countOfBees;
    private int countOfBeesForBest;
    private int countOfBeesForNorm;
    private int countOfBeesForOthers;
    private int countOfBestAreas;
    private int countOfNormAreas;
    private int countOfWorstAreas;
    private int sizeOfArea;
    private DeltaFunction deltaFunction;
    private int iterateCount;
    private double minDelta = 0d;
    private Set<DeltaModel> bests;
    private int quality;

    public BeesAlgorithm getInstance(int countOfAreas, int iterateCount) {
        return new BeesAlgorithmImpl(countOfAreas, iterateCount);
    }

    public BeesAlgorithmImpl() {
    }

    public BeesAlgorithmImpl(int countOfAreas, int iterateCount) {
        startCountOfAreas = countOfAreas;
        countOfBees = countOfAreas > 1000 ? (int) (0.02 * countOfAreas) : 10;
        countOfBeesForBest = (int) (0.3 * countOfBees);
        countOfBeesForNorm = (int) (0.2 * countOfBees);
        countOfBestAreas = (int) (0.2 * countOfAreas);
        countOfNormAreas = (int) (0.3 * countOfAreas);
        sizeOfArea = 1;
        deltaFunction = new DeltaFunction();
        this.iterateCount = iterateCount;
        this.bests = new HashSet<>();
    }

    private void setParameters(int q, int d) {
        this.q = q;
        this.d = d;
        startCountOfAreas = (int) (Math.log(q * d) / Math.log(2));
        countOfBees = (int) (startCountOfAreas * Math.log(q));
        countOfBeesForBest = (int) (0.5 * countOfBees);
        countOfBeesForNorm = (int) (0.3 * countOfBees);
        countOfBeesForOthers = (int) (0.2 * countOfBees);
        countOfBestAreas = (int) (0.2 * startCountOfAreas) + 1;
        countOfNormAreas = (int) (0.3 * startCountOfAreas) + 1;
        countOfWorstAreas = (int) (countOfBestAreas + countOfNormAreas) / 2;
        sizeOfArea = (int) (Math.log(q) / Math.log(2));
        deltaFunction = new DeltaFunction();
        this.iterateCount = q*d > 500 ? 500 : q * d;
        quality = (int) (0.2 * iterateCount);

        this.bests = new HashSet<>();


    }

    public DeltaModel function(int q, int d, double minDelta) {
        setParameters(q, d);
        this.minDelta = minDelta;
        Scope.globalMin = 0;
        Scope.globalMax = q;
        //System.out.println(k);
        Set<int[]> ints = new RandomAlgorithm().generateRandomArrs(q, d, this.startCountOfAreas);
        DeltaFunction.q = q;
        Set<DeltaModel> deltaModels = deltaFunction.deltaFunctionForSet(ints);
        DeltaModel result = new DeltaModel(new int[]{0, 0, 0, 0}, 1d);
        this.bests = new HashSet<>();
        try {
            result = work(deltaModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DeltaModel minOfBest = bests.stream().min(DeltaModel::compareTo).get();
        if (minOfBest.compareTo(result) <= 0) {
            return minOfBest;
        }
        //bests.forEach(System.out::println);
        return result;
        //DeltaModel deltaModel = bests.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();
        //System.out.println(deltaModel);

    }

    public DeltaModel work(Set<DeltaModel> deltaModels) {
        List<DeltaModel> deltaModelList = deltaModels.stream().sorted().collect(Collectors.toList());
        List<DeltaModel> deltaModelsBest = separateSetOfDeltas(deltaModelList, 0, countOfBestAreas);
        List<DeltaModel> deltaModelsNorm = separateSetOfDeltas(deltaModelList, countOfBestAreas, countOfNormAreas + countOfBestAreas);
        List<DeltaModel> deltaModelsWorst = separateSetOfDeltas(deltaModelList,
                countOfNormAreas + countOfBestAreas,
                countOfNormAreas + countOfBestAreas + countOfWorstAreas);
        DeltaModel minModel = deltaModelList.get(0);
        Bee.bestModel = minModel;

        Area bestArea = new Area(countOfBeesForBest, q);
        bestArea.setScope(sizeOfArea);
        bestArea.warmUpBees(deltaModelList.get(0));
        Area normArea = new Area(countOfBeesForNorm, q);
        normArea.setScope(sizeOfArea * 2);
        normArea.warmUpBees(deltaModelList.get(1));
        Area worstArea = new Area(countOfBeesForOthers, q);
        worstArea.setScope(q / sizeOfArea);
        worstArea.warmUpBees(deltaModelList.get(2));

        bests.add(minModel);
        int iterC = iterateCount;

        while (iterC-- != 0) {

            sizeOfArea = updateSize(iterC);
            if(iterC%10==0) {
                bestArea.setScope(sizeOfArea);
                normArea.setScope(sizeOfArea * 2);
                //worstArea.setScope(sizeOfArea * 3);
            }
            bestArea.setModels(deltaModelsBest);
            normArea.setModels(deltaModelsNorm);
            worstArea.setModels(deltaModelsWorst);

            Set<DeltaModel> scopesAndGetDelta = bestArea.createScopesAndGetDelta();
            scopesAndGetDelta.addAll(normArea.createScopesAndGetDelta());
            scopesAndGetDelta.addAll(worstArea.createScopesAndGetDelta());
            System.out.println(iterC);
            System.out.println(scopesAndGetDelta.size());

            if (scopesAndGetDelta.size() == 0) {
                scopesAndGetDelta = bests.stream().collect(Collectors.toSet());
            }

            deltaModelList = scopesAndGetDelta.stream().sorted().collect(Collectors.toList());
            deltaModelsBest = separateSetOfDeltas(deltaModelList,
                    0,
                    countOfBestAreas);
//            deltaRepository.save(deltaModelsBest);
            deltaModelsNorm = separateSetOfDeltas(deltaModelList,
                    countOfBestAreas,
                    countOfNormAreas + countOfBestAreas);
            deltaModelsWorst = separateSetOfDeltas(deltaModelList,
                    countOfNormAreas + countOfBestAreas,
                    countOfNormAreas + countOfBestAreas + countOfWorstAreas);

            iterC = updateMinDelta(deltaModelsBest, minModel, iterC);
        }
        return minModel;
    }

    private int updateMinDelta(List<DeltaModel> deltaModels, DeltaModel minModel, int iterateCount) {
        DeltaModel deltaModel = deltaModels.stream().min(DeltaModel::compareTo).get();
        if (deltaModel != null && deltaModel.getDelta() < minModel.getDelta()) {
            minModel = deltaModel;
            Bee.bestModel = minModel;
            bests.add(minModel);
            quality = (int) (0.2 * iterateCount);
            if (minModel.getDelta() <= minDelta) {
                iterateCount *= 0.5;
                minDelta *= 0.5;
            }
            return iterateCount;
        } else {
            quality--;
            if (quality <= 0) {
                sizeOfArea = sizeOfArea > 1 ? (int) (sizeOfArea * 0.5) : 1;
                quality = (int) (iterateCount * 0.5);
            }
        }
        return iterateCount;
    }

    private List<DeltaModel> separateSetOfDeltas(List<DeltaModel> deltaModels, int start, int end) {
        if (end > deltaModels.size()) {
            end = deltaModels.size() - 1;
        }
        List<DeltaModel> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(deltaModels.get(i));
        }
        return result;
    }

    private int updateSize(int iterateCount) {
        if (iterateCount > 0 && this.iterateCount % iterateCount == 0) {
            sizeOfArea = sizeOfArea > 1 ? (int) (sizeOfArea * 0.5) : 1;
        }

        return sizeOfArea;
    }


}