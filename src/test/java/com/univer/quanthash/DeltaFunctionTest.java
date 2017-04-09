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
        HashSet<Integer> integers = new HashSet<Integer>(setSize);
        for (int i = 0; i < setSize; i++) {
            integers.add(i);
        }
        Double expected = 1.479679754500628E37;
        Double functionValue = deltaFunction.deltaFunction(integers);
        System.out.println(functionValue);
        assertEquals(expected, functionValue);
    }

}