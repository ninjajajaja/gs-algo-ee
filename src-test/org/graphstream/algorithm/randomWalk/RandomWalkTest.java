package org.graphstream.algorithm.randomWalk;

import junit.framework.TestCase;
import org.junit.Test;

public class RandomWalkTest extends TestCase {

    @Test
    public void testGettersAndSetters() {
        RandomWalk rw = new RandomWalk();
        assertEquals("passes", rw.getPassesAttribute());
        assertNull(rw.context.getWeightAttribute());
        assertNotNull(rw.context.getRandom());
        assertEquals(0, rw.context.getEntityMemory());
        assertEquals(1.0, rw.getEvaporation());
        assertEquals(100, rw.getRandomSeed());
        rw.setEntityCount(10);
        assertEquals(0, rw.getEntityCount());
    }

}