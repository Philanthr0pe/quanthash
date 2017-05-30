package com.univer.quanthash.constructive;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by ASUS-PC on 15.05.2017.
 */
public class ConstructiveAlgorithmImplTest {

    ConstructiveAlgorithmImpl constructiveAlgorithm;

    @Before
    public void setUp() throws Exception {
        constructiveAlgorithm = new ConstructiveAlgorithmImpl();
    }

    @Test
    public void generateSSet() throws Exception {
        constructiveAlgorithm.generateSSet(32, 0.5576741839641421)
                .forEach(System.out::println);
    }

    @Test
    public void function() throws Exception {
        System.out.println(constructiveAlgorithm.function(1523, 0.5));
        //System.out.println(constructiveAlgorithm.function(9883, 0.4).getDelta());
        /*System.out.println(constructiveAlgorithm.function(64, 0.3).getDelta());
        System.out.println(constructiveAlgorithm.function(64, 0.2).getDelta());
        System.out.println(constructiveAlgorithm.function(64, 0.1).getDelta());*/
   //     System.out.println(constructiveAlgorithm.function(128, 0.01));
    }

    @Test
    public void generatePrimeSet() throws Exception {
        constructiveAlgorithm.generatePrimeSet(32, 0.5576741839641421)
                .forEach(System.out::println);
    }

    @Test
    public void generateTSet() throws Exception {
        /*constructiveAlgorithm.generateTSet(4093, 0.1)
                .forEach(System.out::println)
                ;*/

    }

}