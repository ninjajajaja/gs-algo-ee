package org.graphstream.algorithm;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.IncompleteGridGenerator;
import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.junit.Test;

public class DStarTest extends TestCase {

    @Test
    public void testDStar() {
        Generator gen = new BarabasiAlbertGenerator();
        AdjacencyListGraph g = new AdjacencyListGraph("g");
        gen.addSink(g);

        gen.begin();
        for (int i = 0; i < 30; i++)
            gen.nextEvents();
        gen.end();

        DStar dstar = new DStar();
        dstar.init(g);

        g.edges().forEach(e -> {
            String id = e.getId();
            dstar.edgeAttributeAdded("", 0, id, "weight", 1.0);
            dstar.edgeAttributeChanged("", 0, id, "weight", 1.0, 2.0);
        });
//
//        dstar.init(Toolkit.randomNode(g), Toolkit.randomNode(g), g);
//
//        do {
//            dstar.compute();
//            dstar.markPath("ui.class", "on", "off");
//
//            DStar.State s = dstar.position;
//
//            while (s.b != dstar.g && s.b != null && r.nextBoolean())
//                s = s.b;
//
//            if (r.nextBoolean() && s != dstar.position && s.b != dstar.g) {
//                g.removeNode(s.node);
//            } else {
//                g.removeEdge(s.node.getEdgeBetween(s.b.node));
//            }
//
//            gen.nextEvents();
//        } while (alive);
//
//        gen.end();
//        dstar.terminate();
    }
}