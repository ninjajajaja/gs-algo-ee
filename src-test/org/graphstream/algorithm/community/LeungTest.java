package org.graphstream.algorithm.community;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.LobsterGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;
import org.graphstream.algorithm.community.Leung;
import static org.junit.Assert.*;

public class LeungTest {

    private Graph graph;
    private Leung algorithm;

    @Before
    public void setUp() {
        graph = new SingleGraph("g");
        Generator gen = new LobsterGenerator();
        gen.addSink(graph);
        gen.begin();
        for(int i = 0; i < 1000; i++)
            gen.nextEvents();
        gen.end();
        // Initialize the algorithm with the test graph
        algorithm = new Leung(graph);
    }

    @Test
    public void testConstructor() {
        assertNotNull(algorithm);
        assertNotNull(new Leung());
        assertNotNull(new Leung(graph));
        assertNotNull(new Leung(graph, "marker"));
        assertNotNull(new Leung(graph, "marker", 0.0, 0.0));
        assertNotNull(new Leung(graph, "marker", "weightMarker", 0.0, 0.0));
        assertNotNull(new Leung(graph, "marker", "weightMarker"));
    }

    @Test
    public void testComputeNodeAndCommunityScores() {
        // Set up the community marker for nodes
        String m = algorithm.getMarker();
        algorithm.compute();
        // Check if the node's community has changed
        assertNotNull(graph.getNode("0001").getAttribute(m));

        // Perform a single iteration of the algorithm to compute community scores
        algorithm.computeNode(graph.getNode("0001"));

        // Check if the community scores have been computed
        assertNotNull(algorithm.communityScores);
    }

    @Test
    public void testOriginateCommunity() {
        Node node = graph.getNode("0042");

        // Perform the originateCommunity method
        algorithm.originateCommunity(node);

        // Check if the node now has a community marker and score
        assertNotNull(node.getAttribute(algorithm.getMarker()));
        assertEquals(1.0, (double) node.getAttribute(algorithm.getMarker() + ".score"), 0.001);
    }

    @Test
    public void testGetRandom() {
        assertNotNull(algorithm.getRandom());
    }

    @Test
    public void testSetRandom() {
        // Create a new Random object
        algorithm.setRandom(new java.util.Random());
        assertNotNull(algorithm.getRandom());
    }

    @Test
    public void testSetParameters() {
        algorithm.setParameters(0.2, 0.1);
        assertEquals(0.2, algorithm.m, 0.001);
        assertEquals(0.1, algorithm.delta, 0.001);
    }

    @Test
    public void testConstructorWithParameters() {
        Leung algorithmWithParams = new Leung(graph, 0.2, 0.1);
        assertEquals(0.2, algorithmWithParams.m, 0.001);
        assertEquals(0.1, algorithmWithParams.delta, 0.001);
    }
}
