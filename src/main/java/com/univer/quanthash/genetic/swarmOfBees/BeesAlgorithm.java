package com.univer.quanthash.genetic.swarmOfBees;

import com.univer.quanthash.models.DeltaModel;

/**
 * Created by Vladislav on 14-Apr-17.
 */
public interface BeesAlgorithm {

    DeltaModel function(int q, int d, double delta);

}