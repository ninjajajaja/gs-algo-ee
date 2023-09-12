package org.graphstream.algorithm;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.URLGenerator;
import org.junit.Test;

public class AlgorithmComputationTriggerTest extends TestCase {

    @Test
    public void testSetterAndGetter() {
        AlgorithmComputationTrigger act = new AlgorithmComputationTrigger(AlgorithmComputationTrigger.Mode.BY_STEP, null);
        act.setMode(AlgorithmComputationTrigger.Mode.BY_STEP);
        assertEquals(AlgorithmComputationTrigger.Mode.BY_STEP, act.getMode());
    }

}