package org.graphstream.algorithm.randomWalk;

import junit.framework.TestCase;
import org.junit.Test;

public class RandomWalkTest extends TestCase {

    @Test
    public void testGettersAndSetters() {
        RandomWalk rw = new RandomWalk();
        assertEquals("passes", rw.getPassesAttribute());
        assertNull(rw.context.weightAttribute);
        assertNotNull(rw.context.random);
        assertEquals(0, rw.context.entityMemory);
        assertEquals(1.0, rw.evaporation);
        assertEquals(100, rw.randomSeed);
        rw.entityCount = 10;
        assertEquals(0, rw.getEntityCount());
    }

}