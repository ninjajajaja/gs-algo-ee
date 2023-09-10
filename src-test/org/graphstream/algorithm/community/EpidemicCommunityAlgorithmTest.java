package org.graphstream.algorithm.community;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.LobsterGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;
import org.graphstream.algorithm.community.EpidemicCommunityAlgorithm;
import static org.junit.Assert.*;

public class EpidemicCommunityAlgorithmTest {

    private Graph graph;
    private EpidemicCommunityAlgorithm algorithm;

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
        algorithm = new EpidemicCommunityAlgorithm(graph);
    }

    @Test
    public void testConstructor() {
        assertNotNull(algorithm);
    }

    @Test
    public void testConstructor2() {
        assertNotNull(new EpidemicCommunityAlgorithm(graph, "marker"));
    }
    @Test
    public void testConstructor3() {
        assertNotNull(new EpidemicCommunityAlgorithm());
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
        Node node = graph.getNode("0008");

        // Perform the originateCommunity method
        algorithm.originateCommunity(node);

        // Check if the node now has a community marker
        assertNotNull(node.getAttribute(algorithm.getMarker()));
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
}
