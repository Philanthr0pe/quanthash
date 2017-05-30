package com.univer.quanthash.random;

import com.univer.quanthash.models.DeltaModel;
import org.junit.Test;

/**
 * Created by ASUS-PC on 27.05.2017.
 */
public class AdaptiveRandomTest {
    @Test
    public void randomDelta() throws Exception {
        DeltaModel deltaModel = new AdaptiveRandom().randomDelta(256, 64);
        System.out.println(deltaModel);

    }

}