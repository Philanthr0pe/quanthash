package com.univer.quanthash.fullbust;

import com.univer.quanthash.models.DeltaModel;

import java.util.HashSet;

/**
 * Created by ASUS-PC on 06.05.2017.
 */
public interface FullBustAlgorithm {

    HashSet<DeltaModel> setOfDeltaFullBust(int q, int d);
}
