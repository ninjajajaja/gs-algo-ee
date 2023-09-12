package org.graphstream.algorithm.coloring.test;

import static org.junit.Assert.assertEquals;

import org.graphstream.algorithm.coloring.WelshPowell;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;


public class WelshPowellTest {

    private WelshPowell wp;
    private Graph graph;

    @Before
    public void setUp() {
        graph = new SingleGraph("TestGraph");
        wp = new WelshPowell("color");
        wp.init(graph);
    }


    @Test
    public void testGraphWithOneNode() {
        graph.addNode("A");
        wp.compute();
        int chromaticNumber = wp.chromaticNumber;
        assertEquals(1, chromaticNumber);
        Node nodeA = graph.getNode("A");
        int color = (int) nodeA.getAttribute("color");
        wp.attrName = "a";
        assertEquals(0, color);
    }

    @Test
    public void testGraphWithDisconnectedNodes() {
        graph.addNode("A");
        graph.addNode("B");
        wp.compute();
        int chromaticNumber = wp.chromaticNumber;
        assertEquals(1, chromaticNumber);
        Node nodeA = graph.getNode("A");
        Node nodeB = graph.getNode("B");
        int colorA = (int) nodeA.getAttribute("color");
        int colorB = (int) nodeB.getAttribute("color");
        wp.attrName = "a";
        assertEquals(0, colorA);
        assertEquals(0, colorB);
    }

    @Test
    public void testGraphWithConnectedNodes() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("AB", "A", "B");
        wp.compute();
        int chromaticNumber = wp.chromaticNumber;
        assertEquals(2, chromaticNumber);
        Node nodeA = graph.getNode("A");
        Node nodeB = graph.getNode("B");
        int colorA = (int) nodeA.getAttribute("color");
        int colorB = (int) nodeB.getAttribute("color");
        wp.attrName = "a";
        assertEquals(1, colorA);
        assertEquals(0, colorB); // Connected nodes should have the same color.
    }

}
