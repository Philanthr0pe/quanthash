package com.univer.quanthash.constructive;

import com.univer.quanthash.DeltaFunction;
import com.univer.quanthash.models.DeltaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 * Created by ASUS-PC on 14.05.2017.
 */
public class ConstructiveAlgorithmImpl {

    private static final Logger log = LoggerFactory.getLogger(ConstructiveAlgorithmImpl.class);

    public DeltaModel function(int q, double eps) {

        eps = round(pow(-log(log(q)/log(2))/log(eps), -1) * 10000d) / 10000d;
        log.info("eps = " + eps);
        List<Integer> tList = generateTSet(q, eps);
        //tList = tList.stream().distinct().collect(Collectors.toList());
        log.info("Size = " + tList.size());
        //tList.stream().sorted().forEach(s -> System.out.print(s+", "));
        int[] ints = tList.stream().mapToInt(i -> i).toArray();

        DeltaFunction.q = q;
        DeltaModel result = new DeltaFunction().deltaFunction(ints);
        double delta = pow(log(q)/log(2), -eps);
        log.info("delta = " + delta);
        log.info("eps = " + -pow(log(log(q)/log(2))/log(delta), -1));

        return result;
    }


    public List<Integer> generatePrimeSet(int q, double eps) {
        ArrayList<Integer> integers = new ArrayList<>();
        double min = pow(log(q) / log(2), 1 + eps) / 2;
        double max = pow(log(q) / log(2), 1 + eps);
        //log.info("Min = " + min + " , Max = " + max);
        int i = (int) min + 1;
        while (i < max) {
            integers.add(i++);
        }
        //log.info("PrinmeMax = " + integers.get(integers.size()-1));
        return integers;
    }

    public List<Integer> generateSSet(int q, double eps) {
        ArrayList<Integer> integers = new ArrayList<>();
        int max = (int) pow(log(q) / log(2), 1 + 2 * eps);
        //log.info("Max = " + max);
        while (max != 0) {
            integers.add(max--);
        }
        return integers;
    }


    public List<Integer> generateTSet(int q, double eps) {
        List<Integer> primeSet = generatePrimeSet(q, eps);
        List<Integer> sSet = generateSSet(q, eps);
        BigInteger qBig = new BigInteger(String.valueOf(q));
        ArrayList<Integer> result = new ArrayList<>(sSet.size()*primeSet.size());
        for (Integer p : primeSet) {
            for (Integer s : sSet) {
                int r = BigInteger.valueOf(p).modInverse(qBig).intValue();
                result.add(s * r);
            }
        }
        return result;
    }


}
