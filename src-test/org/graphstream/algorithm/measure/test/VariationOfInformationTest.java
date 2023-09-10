package org.graphstream.algorithm.measure.test;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.algorithm.generator.LifeGenerator;
import org.graphstream.algorithm.measure.VariationOfInformation;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class VariationOfInformationTest extends TestCase {

    @Test
    public void testCompute() {
        Graph graph = new SingleGraph("g");
        BananaTreeGenerator gen = new BananaTreeGenerator();
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 1000; i++)
            gen.nextEvents();
        gen.end();
        for (int i = 1; i < 300; i++) graph.getNode(i).setAttribute("marker", "aaa");
        for (int i = 301; i < 600; i++) graph.getNode(i).setAttribute("marker", "bbb");
        for (int i = 601; i < 900; i++) graph.getNode(i).setAttribute("marker", "ccc");

        VariationOfInformation varInf = new VariationOfInformation("marker");
        varInf.init(graph);
        varInf.compute();
        assertEquals(0.7784672975540161, varInf.getMeasure());
    }
}