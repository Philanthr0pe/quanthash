package com.univer.quanthash.models;

/**
 * Created by Vladislav on 27-Apr-17.
 */
public class Area {
    private int countOfBees;
    private DeltaModel deltaModel;

    public Area(int countOfBees, DeltaModel deltaModel) {
        this.countOfBees = countOfBees;
        this.deltaModel = deltaModel;
    }

    public int getCountOfBees() {
        return countOfBees;
    }

    public DeltaModel getDeltaModel() {
        return deltaModel;
    }
}
