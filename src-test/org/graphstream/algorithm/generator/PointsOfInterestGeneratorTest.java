package org.graphstream.algorithm.generator;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class PointsOfInterestGeneratorTest extends TestCase {

    @Test
    public void testGeneration() {
        PointsOfInterestGenerator poiGen = new PointsOfInterestGenerator();
        Graph graph = new DefaultGraph("g");
        poiGen.addSink(graph);
        poiGen.begin();
        for(int i = 0; i < 100; i++)
            poiGen.nextEvents();
        poiGen.end();

        poiGen.removePointOfInterest(poiGen.pointsOfInterest.get(3));
        poiGen.addicts.get(0).link(poiGen.addicts.get(1));
    }
}