package com.univer.quanthash.Utils;

import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.models.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 19.04.2017.
 */
public class ScopeUtils {

    DeltaModel deltaModel;
    int scope;

    public ScopeUtils(DeltaModel deltaModel, int scope) {
        this.deltaModel = deltaModel;
        this.scope = scope;
    }

    public List<Scope> generateScopeList() {
        int[] array = deltaModel.getArray();
        ArrayList<Scope> result = new ArrayList<>(array.length);
        for (int i : array) {
            result.add(generateScope(i, scope));
        }

        return result;
    }

    private Scope generateScope(int startPoint, int scope) {
        return new Scope(startPoint - scope, startPoint + scope);
    }
}
