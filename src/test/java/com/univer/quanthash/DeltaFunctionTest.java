package com.univer.quanthash;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by ASUS-PC on 30.03.2017.
 */
public class DeltaFunctionTest {

    DeltaFunction deltaFunction;

    @Before
    public void prepare() {
        deltaFunction = new DeltaFunction();
    }

    @Test
    public void functionTest() {
        Integer setSize = 16;
        int[] integers = new int[16];
        for (int i = 0; i < setSize; i++) {
            integers[i] = i;
        }
        Double expected = 0.06267285198174172;
        Double functionValue = deltaFunction.deltaFunction(integers);
        System.out.println(functionValue);
        assertEquals(expected, functionValue);
    }

}