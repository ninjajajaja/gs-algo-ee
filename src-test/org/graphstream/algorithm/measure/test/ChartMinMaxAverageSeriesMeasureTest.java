package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.measure.ChartMinMaxAverageSeriesMeasure;

public class ChartMinMaxAverageSeriesMeasureTest extends TestCase {

    public void testSetterAndGetter() {
        ChartMinMaxAverageSeriesMeasure cmmasm = new ChartMinMaxAverageSeriesMeasure("name");
        cmmasm.setSeparateMinMaxAxis(true);
        assertTrue(cmmasm.isSeparateMinMaxAxis());
    }

}