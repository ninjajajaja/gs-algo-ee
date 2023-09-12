package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.algorithm.measure.NormalizedMutualInformation;
import org.graphstream.algorithm.measure.VariationOfInformation;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class NormalizedMutualInformationTest extends TestCase {

    @Test
    public void testCompute() {
        Graph graph = new SingleGraph("g");
        BananaTreeGenerator gen = new BananaTreeGenerator();
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 100; i++)
            gen.nextEvents();
        gen.end();
        for (int i = 1; i < 30; i++) graph.getNode(i).setAttribute("marker", "aaa");
        for (int i = 31; i < 60; i++) graph.getNode(i).setAttribute("marker", "bbb");
        for (int i = 61; i < 90; i++) graph.getNode(i).setAttribute("marker", "ccc");

        NormalizedMutualInformation normInf = new NormalizedMutualInformation("marker");
        normInf.init(graph);
        graph.nodes().forEach(n -> normInf.nodeAdded("g", 0, n.getId()));
        for (int i = 1; i < 20; i++) {
            normInf.nodeAttributeChanged("g", 0, String.valueOf(i), "marker", "aaa", "ccc");
        }
        normInf.compute();
        assertEquals(-0.0, normInf.getMeasure());
    }
}