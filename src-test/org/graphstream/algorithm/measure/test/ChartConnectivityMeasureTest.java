package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.measure.ChartConnectivityMeasure;
import org.junit.Test;

public class ChartConnectivityMeasureTest extends TestCase {

    @Test
    public void testGetters() {
        ChartConnectivityMeasure ccm = new ChartConnectivityMeasure(
                new ChartConnectivityMeasure.ChartVertexConnectivityMeasure(),
                new ChartConnectivityMeasure.ChartEdgeConnectivityMeasure()
        );
        assertNotNull(ccm.edgeConnectivity);
        assertNotNull(ccm.vertexConnectivity);
    }

}