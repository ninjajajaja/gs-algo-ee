package org.graphstream.algorithm.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.graphstream.algorithm.generator.WikipediaGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Test;

public class WikipediaGeneratorTest {

    @Test
    public void testGenerator() {

        WikipediaGenerator gen = new WikipediaGenerator(WikipediaGenerator.Lang.EN, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");
        Graph graph = new MultiGraph("g");
        gen.addSink(graph);
        gen.setThreadCount(1); // avoid errors bc of race conditions
        gen.begin();
        for(int i = 0; i < 50; i++)
            gen.nextEvents();
        gen.end();
    }
}
