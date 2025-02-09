package org.graphstream.algorithm.generator;

import junit.framework.TestCase;

public class BarabasiAlbertGeneratorTest extends TestCase {

    public void testSetExactlyMaxLinksPerStep() {
        BarabasiAlbertGenerator bag = new BarabasiAlbertGenerator();
        bag.exactlyMaxLinksPerStep = false;
        assertEquals(1, bag.maxLinksPerStep);
    }
}