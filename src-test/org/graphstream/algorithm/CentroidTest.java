package org.graphstream.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.StringReader;
import java.io.IOException;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.Centroid;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;
import org.junit.Test;
//                     +--- E
// A --- B --- C -- D -|--- F
//                     +--- G

public class CentroidTest {

	@Test
 	public void testCentroid() throws IOException {

        String my_graph = "DGS004\n" + "my 0 0\n" + "an A \n" + "an B \n"
                + "an C \n" + "an D \n" + "an E \n" + "an F \n" + "an G \n"
                + "ae AB A B \n" + "ae BC B C \n" + "ae CD C D \n" + "ae DE D E \n"
                + "ae DF D F \n" + "ae DG D G \n";

 		Graph graph = new DefaultGraph("Centroid Test");
 		StringReader reader = new StringReader(my_graph);

 		FileSourceDGS source = new FileSourceDGS();
 		source.addSink(graph);
 		source.readAll(reader);

 		APSP apsp = new APSP();
 		apsp.init(graph);
 		apsp.compute();

 		Centroid centroid = new Centroid();
 		centroid.init(graph);
 		centroid.compute();

// 		graph.nodes().forEach(n -> {
// 			Boolean in = (Boolean) n.getAttribute("centroid");
//
// 			System.out.printf("%s is%s in the centroid.\n", n.getId(), in ? ""
// 					: " not");
// 		});

	// Output will be :
	//
	// A is not in the centroid
	// B is not in the centroid
	// C is not in the centroid
	// D is in the centroid
	// E is not in the centroid
	// F is not in the centroid
	// G is not in the centroid

		centroid.setCentroidAttribute("ca");
		assertEquals("ca",centroid.getCentroidAttribute());

		centroid.setAPSPInfoAttribute(null);
		assertNull(centroid.getAPSPInfoAttribute());

		centroid.setIsInCentroidValue(null);
		assertNull(centroid.getIsInCentroidValue());

		centroid.setIsNotInCentroidValue(null);
		assertNull(centroid.getIsNotInCentroidValue());
	}
}