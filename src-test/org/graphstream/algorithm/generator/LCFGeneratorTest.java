package org.graphstream.algorithm.generator;

import junit.framework.TestCase;
import org.graphstream.algorithm.generator.LCFGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Test;

public class LCFGeneratorTest extends TestCase {

    @Test
    public void testGeneration() {
        Graph graph = new MultiGraph("g");
        LCFGenerator lcfGen = new LCFGenerator(new LCFGenerator.LCF(10, new int[]{1,2,3,4,5}), 10, true);
        lcfGen.addSink(graph);
        lcfGen.begin();
        for(int i = 0; i < 100; i++)
            lcfGen.nextEvents();
        lcfGen.end();
    }


}