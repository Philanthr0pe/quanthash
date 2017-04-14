package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        int[] array = {7,7,6,7};
        DeltaModel functionValue = deltaFunction.deltaFunction(array);
        System.out.println(functionValue);
        assertNotNull(functionValue);
    }

}