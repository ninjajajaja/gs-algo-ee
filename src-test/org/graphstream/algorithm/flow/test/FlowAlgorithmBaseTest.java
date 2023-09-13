package org.graphstream.algorithm.flow.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import org.junit.Test;

public class FlowAlgorithmBaseTest extends TestCase {

    @Test
    public void testSettersAndGetters() {
        FlowAlgorithmBase fab = new FlowAlgorithmBase() {

            @Override
            public double getMaximumFlow() {
                return 0;
            }

            @Override
            public void compute() {
                // nothing
            }
        };

        fab.setCapacityAttribute("capAttr");
        assertNotNull(fab.getMaximumFlow());
        assertEquals("capAttr", fab.capacityAttribute);

    }

}