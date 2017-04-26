package com.univer.quanthash.Utils;

import com.univer.quanthash.models.Scope;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by ASUS-PC on 19.04.2017.
 */
public class ScopeListUtils {
    private List<Scope> scopeList;

    public ScopeListUtils(List<Scope> scopeList) {
        this.scopeList = scopeList;
    }

    public Set<int[]> generateArrsByScope(int size) {
        int d = scopeList.size();
        HashSet<int[]> result = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            int[] array = new int[d];
            int element;
            Scope scope;
            for (int j = 0; j < d; j++) {
                scope = scopeList.get(j);
                element = new Random().ints(scope.getMin(), scope.getMax()).findFirst().getAsInt();
                array[j] = element;
            }
            result.add(array);
        }
        return result;
    }
}
