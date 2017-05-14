package com.univer.quanthash.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by Vladislav on 10-Apr-17.
 */

@Entity
public class DeltaModel implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int[] array;
    private double delta;
    private String type;

    protected DeltaModel() {}

    public DeltaModel(int[] array, double delta) {
        this.array = array;
        this.delta = delta;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[] getArray() {
        return array;
    }

    public double getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        return "DeltaModel{" +
                "id=" + id +
                ", array=" + Arrays.toString(array) +
                ", delta=" + delta +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        DeltaModel o1 = (DeltaModel) o;
        return Double.compare(this.getDelta(), o1.getDelta());
    }
}
