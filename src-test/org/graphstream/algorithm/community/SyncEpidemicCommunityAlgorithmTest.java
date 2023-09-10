package org.graphstream.algorithm.community;

import org.graphstream.algorithm.generator.FullGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.LobsterGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;
import org.graphstream.algorithm.community.SyncEpidemicCommunityAlgorithm;

import static org.junit.Assert.*;

public class SyncEpidemicCommunityAlgorithmTest {

    private Graph graph;
    private SyncEpidemicCommunityAlgorithm algorithm;

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
        algorithm = new SyncEpidemicCommunityAlgorithm(graph);
    }

    @Test
    public void testConstructor() {
        assertNotNull(algorithm);
        assertNotNull(new Leung());
        assertNotNull(new Leung(graph, "marker"));
    }

    @Test
    public void testTerminate() {
        algorithm.terminate();
        assertEquals(0, algorithm.iteration);
    }

    @Test
    public void testCompute() {
        algorithm.compute();
        assertEquals(1, algorithm.iteration);
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
}
