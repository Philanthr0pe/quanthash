package com.univer.quanthash;

import com.univer.quanthash.models.DeltaModel;
import org.junit.Before;
import org.junit.Test;

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
        Integer setSize = 32;
        DeltaFunction.q = setSize;
        //Double expected = 0.06267285198174172;
        int[] array = new int[]{16, 12, 28, 0};
        DeltaModel functionValue = deltaFunction.deltaFunction(array);
        System.out.println(functionValue);

        assertNotNull(functionValue);
    }

}