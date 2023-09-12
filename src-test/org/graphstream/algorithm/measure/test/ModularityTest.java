package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.algorithm.generator.ChvatalGenerator;
import org.graphstream.algorithm.generator.LifeGenerator;
import org.graphstream.algorithm.measure.Modularity;
import org.graphstream.algorithm.measure.NormalizedMutualInformation;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class ModularityTest extends TestCase {

    @Test
    public void testCompute() {
        Graph graph = new SingleGraph("g");
        //boolean[] data = new boolean[100];
        //data[5] = true;
        BananaTreeGenerator gen = new BananaTreeGenerator();
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();
        for (int i = 1; i < 30; i++) graph.getNode(i).setAttribute("marker", "aaa");
        for (int i = 31; i < 60; i++) graph.getNode(i).setAttribute("marker", "bbb");
        for (int i = 61; i < 90; i++) graph.getNode(i).setAttribute("marker", "ccc");

        Modularity mod = new Modularity("marker");
        mod.init(graph);
        graph.nodes().forEach(n -> mod.nodeAdded("g", 0, n.getId()));
        for (int i = 1; i < 20; i++) {
            mod.nodeAttributeChanged("g", 0, String.valueOf(i), "marker", "aaa", "ccc");
        }
        mod.compute();
        assertEquals(0.2655875, mod.getMeasure());

        mod.setWeightMarker("wm");

    }
}