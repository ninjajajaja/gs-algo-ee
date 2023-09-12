package org.graphstream.algorithm;

import junit.framework.TestCase;

public class SpectrumTest extends TestCase {

    public void testGetLargestEigenvalue() {

        double[] vals = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 100.0, 6.0, 7.0, 8.0, 9., 10.0};
        Spectrum spectrum = new Spectrum();
        assertEquals(100.0, spectrum.getLargestEigenvalue(vals));
    }
}