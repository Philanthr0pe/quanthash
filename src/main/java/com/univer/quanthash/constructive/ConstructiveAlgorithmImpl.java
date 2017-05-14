package com.univer.quanthash.constructive;

import com.univer.quanthash.models.DeltaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * Created by ASUS-PC on 14.05.2017.
 */
public class ConstructiveAlgorithmImpl {

    private static final Logger log = LoggerFactory.getLogger(ConstructiveAlgorithmImpl.class);

    public DeltaModel function(int q, double eps) {

        DeltaModel result = null;

        eps = -pow(log(log(q)/log(2))/log(eps),-1);
        System.out.println(eps);



        //new DeltaFunction().deltaFunction()

        return result;
    }



    public List<Integer> generatePrimeSet(int q, double eps) {
        ArrayList<Integer> integers = new ArrayList<>();
        double min = pow(log(q)/log(2), 1 + eps) / 2;
        double max = pow(log(q)/log(2), 1 + eps);
        log.info("Min = " + min + " , Max = " + max);
        int i = (int) min + 1;
        while (i < max) {
            integers.add(i++);
        }
        return integers;
    }

    public List<Integer> generateSSet(int q, double eps) {
        ArrayList<Integer> integers = new ArrayList<>();
        int max = (int) pow(log(q)/log(2), 1 + 2 * eps);
        log.info("Max = " + max);
        while (max !=0) {
            integers.add(max--);
        }
        return integers;
    }



    public Set<Integer> generateTSet(int q, double eps) {
        List<Integer> primeSet = generatePrimeSet(q, eps);
        List<Integer> sSet = generateSSet(q, eps);
        HashSet<Integer> result = new HashSet<>();
        double[] doubles = primeSet.stream()
                .mapToDouble(p -> pow(p, -1))
                .toArray();
        for (Integer integer : sSet) {
            for (double aDouble : doubles) {
                double d = aDouble * integer;
                if (d % 1.0 == 0.0) {
                    result.add((int) d);
                }
            }
        }
        System.out.println(result.size());
        return result;
    }


}
