package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.LifeGenerator;
import org.graphstream.algorithm.measure.ElementCountMeasure;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class ElementCountMeasureTest extends TestCase {

    public void testGetElementCount() {

        Graph graph = new SingleGraph("g");
        BarabasiAlbertGenerator gen = new BarabasiAlbertGenerator();
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 200; i++)
            gen.nextEvents();
        gen.end();

        ElementCountMeasure.EdgeCountMeasure ecm = new ElementCountMeasure.EdgeCountMeasure();
        ecm.init(graph);
        assertEquals(201, ecm.getElementCount());

        ElementCountMeasure.NodeCountMeasure ncm = new ElementCountMeasure.NodeCountMeasure();
        ncm.init(graph);
        assertEquals(202, ncm.getElementCount());
    }
}