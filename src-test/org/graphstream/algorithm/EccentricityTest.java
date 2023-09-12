package org.graphstream.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.StringReader;
import java.io.IOException;
import org.graphstream.algorithm.Centroid;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;
import org.junit.Test;

//                     +--- E
 // A --- B --- C -- D -|--- F
 //                     +--- G

public class EccentricityTest {

    @Test
 	public void testCompute() throws IOException {

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
 
 		Eccentricity eccentricity = new Eccentricity();
 		eccentricity.init(graph);
 		eccentricity.compute();
 
// 		graph.nodes().forEach(n -> {
// 			Boolean in = (Boolean) n.getAttribute("eccentricity");
//
// 			System.out.printf("%s is%s in the eccentricity.\n", n.getId(), in ? ""
// 					: " not");
// 		});
 
 		// Output will be :
 		//
 		// A is not in the eccentricity
 		// B is not in the eccentricity
 		// C is in the eccentricity
 		// D is not in the eccentricity
 		// E is not in the eccentricity
 		// F is not in the eccentricity
 		// G is not in the eccentricity

		eccentricity.setEccentricityAttribute("ea");
		assertEquals("ea", eccentricity.getEccentricityAttribute());
		eccentricity.setIsInEccentricityValue(null);
		assertNull(eccentricity.getIsInEccentricityValue());
		eccentricity.setIsNotInEccentricityValue(null);
		assertNull(eccentricity.getIsNotInEccentricityValue());
		eccentricity.setAPSPInfoAttribute("a");
		assertEquals("a", eccentricity.getAPSPInfoAttribute());
 	}
}