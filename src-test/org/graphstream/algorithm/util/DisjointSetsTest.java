package org.graphstream.algorithm.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class DisjointSetsTest {

    private DisjointSets<Integer> disjointSets;

    @Before
    public void setUp() {
        disjointSets = new DisjointSets<>();
    }

    @Test
    public void testAdd() {
        assertTrue(disjointSets.add(1));
        assertTrue(disjointSets.contains(1));

        assertFalse(disjointSets.add(1)); // Adding the same element again should return false
        assertTrue(disjointSets.add(2));
    }

    @Test
    public void testInSameSet() {
        disjointSets.add(1);
        disjointSets.add(2);
        disjointSets.add(3);

        assertTrue(disjointSets.inSameSet(1, 1)); // An element is in the same set as itself
        assertFalse(disjointSets.inSameSet(1, 2)); // Initially, different elements are in different sets

        disjointSets.union(1, 2); // Union two elements
        assertTrue(disjointSets.inSameSet(1, 2)); // They should be in the same set now
        assertFalse(disjointSets.inSameSet(1, 3));
    }

    @Test
    public void testUnion() {
        disjointSets.add(1);
        disjointSets.add(2);
        disjointSets.add(3);

        assertTrue(disjointSets.union(1, 2)); // Union two elements
        assertTrue(disjointSets.inSameSet(1, 2));

        assertFalse(disjointSets.union(1, 2)); // Union the same elements again, should return false

        assertTrue(disjointSets.union(1, 3)); // Elements in different sets, should return false
        assertTrue(disjointSets.inSameSet(1, 2));

        assertFalse(disjointSets.union(2, 3)); // Union elements in different sets
        assertTrue(disjointSets.inSameSet(1, 3)); // All three should be in the same set now
    }

    @Test
    public void testContains() {
        assertFalse(disjointSets.contains(1));

        disjointSets.add(1);
        assertTrue(disjointSets.contains(1));
    }

    @Test
    public void testClear() {
        disjointSets.add(1);
        disjointSets.add(2);
        disjointSets.add(3);

        assertTrue(disjointSets.contains(1));
        assertTrue(disjointSets.contains(2));
        assertTrue(disjointSets.contains(3));

        disjointSets.clear();

        assertFalse(disjointSets.contains(1));
        assertFalse(disjointSets.contains(2));
        assertFalse(disjointSets.contains(3));
    }
}
