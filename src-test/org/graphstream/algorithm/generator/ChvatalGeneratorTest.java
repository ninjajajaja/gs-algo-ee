package org.graphstream.algorithm.generator;

import junit.framework.TestCase;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Test;

public class ChvatalGeneratorTest extends TestCase {

    @Test
    public void testGeneration() {
        Graph graph = new MultiGraph("g");
        Generator gen = new ChvatalGenerator();
        gen.addSink(graph);
        gen.begin();
        for(int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();
    }

}