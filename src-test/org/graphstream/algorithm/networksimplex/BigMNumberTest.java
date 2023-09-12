package org.graphstream.algorithm.networksimplex;

import junit.framework.TestCase;

public class BigMNumberTest extends TestCase {

    public void testGetSmall() {
        BigMNumber bn = new BigMNumber(5);
        assertEquals(5, bn.getSmall());
    }
}