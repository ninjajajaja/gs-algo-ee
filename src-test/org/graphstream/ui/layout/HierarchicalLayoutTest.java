package org.graphstream.ui.layout;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.graphstream.ui.view.Viewer;
import org.junit.Test;

public class HierarchicalLayoutTest extends TestCase {

    @Test
    public void testHLayout() {
        Graph g = new AdjacencyListGraph("g");
        BarabasiAlbertGenerator gen = new BarabasiAlbertGenerator();
        HierarchicalLayout hl = new HierarchicalLayout();
        gen.addSink(g);
        gen.begin();
        for (int i = 0; i < 200; i++)
            gen.nextEvents();
        gen.end();

        hl.internalGraph = g;
        hl.structureChanged = true;
        hl.compute();
        hl.setRoots("1", "10", "42", "43", "44", "45", "46", "101", "156", "169");
        hl.compute();

    }

}