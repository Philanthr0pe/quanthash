package com.univer.quanthash;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < setSize; i++) {
            integers.add(i);
        }


        Double expected = 1.479679754500628E37;
        Double functionValue = deltaFunction.deltaFunction(integers, setSize);
        System.out.println(functionValue);
        assertEquals(expected, functionValue);
    }

}