package org.graphstream.algorithm.generator;

import junit.framework.TestCase;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class LifeGeneratorTest extends TestCase {

    @Test
    public void testGeneration() {
        Graph graph = new SingleGraph("g");
        boolean[] data = new boolean[100];
        data[0] = true;
        LifeGenerator gen = new LifeGenerator(10, 10, data);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 500; i++)
            gen.nextEvents();
        gen.end();
        gen.tore = true;
        gen.pushCoords = true;
    }
}