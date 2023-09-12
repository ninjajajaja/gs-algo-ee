package org.graphstream.algorithm.generator;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.IncompleteGridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class IncompleteGridGeneratorTest extends TestCase {

    @Test
    public void testGeneration() {
        Graph graph = new MultiGraph("g");
        Generator gen = new IncompleteGridGenerator(false, 0.6f, 3, 3);
        gen.addSink(graph);
        gen.begin();
        for(int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();
    }
}