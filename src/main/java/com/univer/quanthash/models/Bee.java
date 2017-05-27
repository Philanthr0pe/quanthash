package com.univer.quanthash.models;

import com.univer.quanthash.DeltaFunction;

import java.util.Random;

/**
 * Created by ASUS-PC on 04.05.2017.
 */
public class Bee {
    public static DeltaModel bestModel;
    public static double weight = 0.05;
    private DeltaModel localBest;
    private DeltaModel prevModel;
    private double speed[];
    private double scope1;
    private double scope2;
    private int scope;
    private int q;

    public Bee(int q) {
        this.q = q;
    }

    public void setScope(int scope) {
        this.scope1 = scope * 0.05;
        this.scope2 = 2 * scope * 0.1;
        this.scope = scope;
        //System.out.println(scope1 +  " scopes " + scope2 );
    }

    public DeltaModel generateDeltaModel(DeltaModel model) {
        int[] array = model.getArray();
        int[] local = localBest.getArray();
        int[] global = bestModel.getArray();
        //double[] newSpeed = new double[speed.length];
        for (int i = 0; i < array.length; i++) {
            int max = array[i] + scope;
            int min = array[i] - scope;
            array[i] = new Random().ints(min, max + 1).findFirst().getAsInt();
        }
        //speed = newSpeed;
        array = checkBorder(array);
        return countDelta(array);
    }

    private int[] checkBorder(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                array[i] = 0;
                //System.out.println(array[i]);
            }
            if (array[i] > q - 1) {
                array[i] = q-1;
                //System.out.println(array[i]);
            }
        }
        return array;
    }

    private DeltaModel countDelta(int[] array) {
        DeltaModel model = new DeltaFunction(q).deltaFunction(array);
        if (localBest == null || model.compareTo(localBest) < 0) {
            localBest = new DeltaModel(model.getArray(), model.getDelta());
        }
        return model;
    }

    public void initSpeed(DeltaModel deltaModel) {
        int[] array = deltaModel.getArray();
        int[] global = bestModel.getArray();
        speed = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            speed[i] = scope1 * (Math.random()*0.5 * (array[i] - 0))
                    + scope2 * (Math.random()*0.5 * (global[i] - 0));
            //System.out.print(speed[i] + "; ");
        }
        //System.out.println();
        localBest = deltaModel;
        prevModel = deltaModel;
    }
}
