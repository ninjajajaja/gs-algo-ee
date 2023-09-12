package org.graphstream.algorithm;

import junit.framework.TestCase;

import static org.junit.Assert.*;
import org.junit.Test;
import org.graphstream.algorithm.FixedArrayList;

public class FixedArrayListTest {

    @Test
    public void testEquals() {
        FixedArrayList<Integer> list1 = new FixedArrayList<>();
        FixedArrayList<Integer> list2 = new FixedArrayList<>();

        // Add elements to both lists
        for (int i = 0; i < 100; i++) {
            list1.add(i);
            list2.add(i);
        }

        // Check that the two lists are equal
        assertTrue(list1.equals(list2));

        // add element to list2
        list2.remove(99);
        list2.add(-1);

        // Check that the lists are not equal anymore
        assertFalse(list1.equals(list2));

        // Create an empty list
        FixedArrayList<Integer> list3 = new FixedArrayList<>();

        // Check that the empty list is not equal to the non-empty list
        assertFalse(list1.equals(list3));

        assertEquals(99, list1.getLastIndex());
        assertEquals(99, list2.getLastIndex());
        assertEquals(-1, list3.getLastIndex());
    }
}
