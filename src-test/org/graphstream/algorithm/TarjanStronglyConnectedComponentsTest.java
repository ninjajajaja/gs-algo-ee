package org.graphstream.algorithm;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.IncompleteGridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class TarjanStronglyConnectedComponentsTest extends TestCase {

    @Test
    public void testTarjan() {
        Graph graph = new SingleGraph("g");
        Generator gen = new BananaTreeGenerator();
        gen.addSink(graph);
        gen.begin();
        for(int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();

        TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
        tscc.init(graph);
        tscc.compute();
    }

}