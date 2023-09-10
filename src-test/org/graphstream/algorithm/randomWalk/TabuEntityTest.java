package org.graphstream.algorithm.randomWalk;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.IncompleteGridGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Test;

public class TabuEntityTest extends TestCase {

    @Test
    public void testTabu() {
        Graph graph = new MultiGraph("g");
        Generator gen = new IncompleteGridGenerator(false, 0.4f, 2, 3);
        gen.addSink(graph);
        gen.begin();
        for(int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();

        RandomWalk rwalk = new RandomWalk();
        rwalk.setEntityCount(graph.getNodeCount() / 2);
        rwalk.setEntityMemory(5);
        rwalk.init(graph);
        for (int i = 0; i < 100; i++) {
            rwalk.compute();
        }
        rwalk.terminate();

    }
}