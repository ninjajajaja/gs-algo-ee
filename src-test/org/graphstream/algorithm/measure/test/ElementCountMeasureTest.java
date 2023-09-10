package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.LifeGenerator;
import org.graphstream.algorithm.measure.ElementCountMeasure;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class ElementCountMeasureTest extends TestCase {

    public void testGetElementCount() {

        Graph graph = new SingleGraph("g");
        boolean[] data = new boolean[100];
        data[0] = true;
        LifeGenerator gen = new LifeGenerator(20, 5, data);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();

        ElementCountMeasure.EdgeCountMeasure ecm = new ElementCountMeasure.EdgeCountMeasure();
        ecm.init(graph);
        assertEquals(0.0, ecm.getElementCount());

        ElementCountMeasure.NodeCountMeasure ncm = new ElementCountMeasure.NodeCountMeasure();
        ncm.init(graph);
        assertEquals(0.0, ncm.getElementCount());
    }
}