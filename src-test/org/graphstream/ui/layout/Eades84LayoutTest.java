package org.graphstream.ui.layout;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.algorithm.generator.LifeGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class Eades84LayoutTest extends TestCase {

    @Test
    public void testEades() {
        Graph graph = new SingleGraph("g");
        BananaTreeGenerator gen = new BananaTreeGenerator();
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 200; i++)
            gen.nextEvents();
        gen.end();

        Eades84Layout eades = new Eades84Layout();
        graph.nodes().forEach(n -> eades.nodeAdded("", 0, n.getId()));
        graph.edges().forEach(e -> eades.edgeAdded("", 0, e.getId(), e.getSourceNode().getId(), e.getTargetNode().getId(), false));
        eades.compute();
        graph.nodes().forEach(n -> eades.nodeRemoved("", 0, n.getId()));
        graph.edges().forEach(e -> eades.edgeRemoved("", 0, e.getId()));


    }

}