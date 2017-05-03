package com.univer.quanthash.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladislav on 27-Apr-17.
 */
public class Area {
    private int countOfBees;
    private Set<DeltaModel> deltaModel;
    private int scope;

    public Area(int countOfBees, Set<DeltaModel> deltaModel, int scope) {
        this.countOfBees = countOfBees;
        this.deltaModel = deltaModel;
        this.scope = scope;
    }

    public Set<DeltaModel> createScopesAndGetDelta() {
        Set<DeltaModel> result = new HashSet<>();
        for (DeltaModel model : deltaModel) {
            result.addAll(new Scope(model.getArray(), scope)
                    .generateScopesAndStartBees(countOfBees));
        }
        return result;
    }




    public int getCountOfBees() {
        return countOfBees;
    }

    public Set<DeltaModel> getDeltaModel() {
        return deltaModel;
    }
}
