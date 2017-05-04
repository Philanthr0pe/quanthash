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
        Integer setSize = 512;
        DeltaFunction.q = setSize;
        Double expected = 0.06267285198174172;
        int[] array ={33, 84, 225, 225, 201, 122, 66, 70, 65, 147, 25, 79, 207, 41, 37, 153, 167, 95, 174, 180, 181, 122, 108, 251, 67, 210, 2, 76, 176, 85, 49, 80, 250, 36, 230, 231, 171, 47, 208, 106, 89, 164, 115, 121, 180, 0, 195, 210, 163, 205, 169, 21, 228, 142, 255, 191, 74, 24, 139, 183, 161, 129, 213, 222, 25, 17, 107, 69, 56, 87, 152, 162, 251, 152, 149, 136, 72, 101, 91, 239, 101, 172, 201, 22, 129, 82, 189, 52, 249, 253, 220, 126, 71, 39, 26, 22, 174, 158, 75, 84, 218, 218, 83, 48, 89, 135, 200, 41, 117, 123, 61, 148, 222, 95, 159, 195, 161, 110, 198, 7, 185, 223, 83, 131, 160, 32, 24, 76};
        DeltaModel functionValue = deltaFunction.deltaFunction(array);
        System.out.println(functionValue);
        assertNotNull(functionValue);
    }

}