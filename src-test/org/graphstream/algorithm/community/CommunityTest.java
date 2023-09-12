package org.graphstream.algorithm.community;

import org.graphstream.algorithm.community.Community;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommunityTest {

    private Community community1;
    private Community community2;

    @Before
    public void setUp() {
        community1 = new Community();
        community2 = new Community();
    }

    @Test
    public void testConstructor() {
        assertNotNull(community1);
        assertNotNull(community2);
    }

    @Test
    public void testCommunityId() {
        assertNotNull(community1.id());
        assertNotNull(community2.id());
        assertNotEquals(community1.id(), community2.id());
    }

    @Test
    public void testGetId() {
        assertNotNull(community1.getId());
        assertNotNull(community2.getId());
        assertNotEquals(community1.getId(), community2.getId());
    }

    @Test
    public void testEquals() {
        assertTrue(community1.equals(community1));
        assertFalse(community1.equals(community2));
    }

    @Test
    public void testToString() {
        assertNotNull(community1.toString());
        assertNotNull(community2.toString());
        assertNotEquals(community1.toString(), community2.toString());
    }

    @Test
    public void testCompareTo() {
        assertTrue(community1.compareTo(community1) == 0);
        assertTrue(community1.compareTo(community2) != 0);
    }
}
