package com.univer.quanthash.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vladislav on 27-Apr-17.
 */
public class Area{
    private int countOfBees;
    private List<DeltaModel> deltaModel;
    private List<Bee> bees;
    private int q;
    private int d;
    private int scope;

    public Area(int countOfBees, int q) {
        this.countOfBees = countOfBees;
        this.bees = new ArrayList<>();
        for (int i = 0; i < countOfBees; i++) {
            bees.add(new Bee(q));
        }
    }

    public void setScope(int scope) {
        this.scope = scope;
        for (Bee bee : bees) {
            bee.setScope(scope);
        }
    }

    public void setModels(List<DeltaModel> deltaModel) {
        this.deltaModel = deltaModel;
    }

    public Set<DeltaModel> createScopesAndGetDelta() {
        Set<DeltaModel> result = new HashSet<>();
        for (Bee bee : bees) {
            for (DeltaModel model : deltaModel) {
                DeltaModel resModel = bee.generateDeltaModel(model);
                result.add(resModel);
            }
        }
        return result;
    }

    public void warmUpBees(DeltaModel deltaModel) {
        int beesSize = bees.size();
        for (int i = 0; i < bees.size(); i++) {
            bees.get(i).initSpeed(deltaModel);
        }
    }

    public int getCountOfBees() {
        return countOfBees;
    }

    public List<DeltaModel> getDeltaModel() {
        return deltaModel;
    }
}
