package org.graphstream.algorithm.community;

import java.util.ArrayList;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DecentralizedCommunityAlgorithmTest {

    private DecentralizedCommunityAlgorithm algorithm;
    private Graph graph;

    @Before
    public void setUp() {
        graph = new DefaultGraph("dg");
        for (int i = 0; i < 10; i++) graph.addNode(String.valueOf(i));
        algorithm = new TestDecentralizedCommunityAlgorithm(graph);
        graph.nodes().forEach(n -> algorithm.originateCommunity(n));
    }

    @Test
    public void testInit() {
        algorithm.init(graph);
        assertEquals(graph, algorithm.graph);

        algorithm.init(graph, "marker");
        assertEquals(graph, algorithm.graph);
    }

    @Test
    public void testSetMarker() {
        String marker = "testMarker";
        algorithm.setMarker(marker);
        assertEquals(marker, algorithm.nonUniqueMarker);
        assertTrue(algorithm.marker.startsWith(algorithm.getClass().getName()));
    }

    @Test
    public void testStaticMode() {
        algorithm.staticMode();
        assertTrue(algorithm.staticMode);
    }

    @Test
    public void testSetRandom() {
        Random rng = new Random();
        algorithm.setRandom(rng);
        assertEquals(rng, algorithm.rng);
    }

    @Test
    public void testGetRandom() {
        Random rng = new Random();
        algorithm.setRandom(rng);
        assertEquals(rng, algorithm.getRandom());
    }

    @Test
    public void testCompute() {
        graph.nodes().forEach(n -> algorithm.originateCommunity(n));
        String marker = "community";
        algorithm.setMarker(marker);
        algorithm.compute();
        assertFalse(algorithm.graphChanged);

        algorithm.staticMode();
        algorithm.graphChanged = true;
        algorithm.compute();
        assertTrue(algorithm.graphChanged);
    }

    @Test
    public void testOriginateCommunity() {
        Node node = graph.getNode(0);
        algorithm.originateCommunity(node);
        assertNotNull(node.getAttribute(algorithm.marker));
    }

    @Test
    public void testUpdateDisplayClass() {
        Node node = graph.getNode(5);
        Community mockCommunity = new Community();
        node.setAttribute(algorithm.marker, mockCommunity);

        algorithm.updateDisplayClass(node);
    }

    @Test
    public void testAttributeChanged() {
        Node node = graph.getNode(3);
        algorithm.attributeChanged(node, "attribute", "oldValue", "newValue");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testNodeAdded() {
        algorithm.nodeAdded("graphId", 123L, "nodeId");
        assertTrue(algorithm.graphChanged);
    }

    @Test
    public void testNodeRemoved() {
        algorithm.nodeRemoved("graphId", 123L, "nodeId");
        assertTrue(algorithm.graphChanged);
    }

    @Test
    public void testEdgeAdded() {
        algorithm.edgeAdded("graphId", 123L, "edgeId", "fromNodeId", "toNodeId", true);
        assertTrue(algorithm.graphChanged);
    }

    @Test
    public void testEdgeRemoved() {
        algorithm.edgeRemoved("graphId", 123L, "edgeId");
        assertTrue(algorithm.graphChanged);
    }

    @Test
    public void testGraphCleared() {
        algorithm.graphCleared("graphId", 123L);
        assertTrue(algorithm.graphChanged);
    }

    @Test
    public void testStepBegins() {
        algorithm.stepBegins("graphId", 123L, 0.5);
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testGraphAttributeAdded() {
        algorithm.graphAttributeAdded("graphId", 123L, "attribute", "value");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testGraphAttributeChanged() {
        algorithm.graphAttributeChanged("graphId", 123L, "attribute", "oldValue", "newValue");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testGraphAttributeRemoved() {
        algorithm.graphAttributeRemoved("graphId", 123L, "attribute");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testNodeAttributeAdded() {
        algorithm.nodeAttributeAdded("graphId", 123L, "nodeId", "attribute", "value");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testNodeAttributeChanged() {
        algorithm.nodeAttributeChanged("graphId", 123L, "nodeId", "attribute", "oldValue", "newValue");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testNodeAttributeRemoved() {
        algorithm.nodeAttributeRemoved("graphId", 123L, "nodeId", "attribute");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testEdgeAttributeAdded() {
        algorithm.edgeAttributeAdded("graphId", 123L, "edgeId", "attribute", "value");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testEdgeAttributeChanged() {
        algorithm.edgeAttributeChanged("graphId", 123L, "edgeId", "attribute", "oldValue", "newValue");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    @Test
    public void testEdgeAttributeRemoved() {
        algorithm.edgeAttributeRemoved("graphId", 123L, "edgeId", "attribute");
        // No specific behavior to check, just to ensure method call doesn't cause errors.
    }

    // Custom TestDecentralizedCommunityAlgorithm class for testing
    private static class TestDecentralizedCommunityAlgorithm extends DecentralizedCommunityAlgorithm {
        TestDecentralizedCommunityAlgorithm(Graph graph) {
            super(graph);
        }

        TestDecentralizedCommunityAlgorithm() {
            super();
        }

        TestDecentralizedCommunityAlgorithm(Graph graph, String marker) {
            super(graph, marker);
        }

        @Override
        public void computeNode(Node node) {
            // Custom computeNode implementation for testing
        }
    }

}
