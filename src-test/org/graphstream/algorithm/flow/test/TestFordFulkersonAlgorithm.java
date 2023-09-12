/*
 * This file is part of GraphStream <http://graphstream-project.org>.
 * 
 * GraphStream is a library whose purpose is to handle static or dynamic
 * graph, create them from scratch, file or any source and display them.
 * 
 * This program is free software distributed under the terms of two licenses, the
 * CeCILL-C license that fits European law, and the GNU Lesser General Public
 * License. You can  use, modify and/ or redistribute the software under the terms
 * of the CeCILL-C license as circulated by CEA, CNRS and INRIA at the following
 * URL <http://www.cecill.info> or under the terms of the GNU LGPL as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C and LGPL licenses and that you accept their terms.
 *
 *
 * @since 2012-02-10
 * 
 * @author Guilhelm Savin <guilhelm.savin@graphstream-project.org>
 */
package org.graphstream.algorithm.flow.test;

import java.io.IOException;
import java.io.InputStream;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.algorithm.flow.FlowAlgorithm;
import org.graphstream.algorithm.flow.FordFulkersonAlgorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class TestFordFulkersonAlgorithm extends TestFlowAlgorithm {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.graphstream.algorithm.flow.test.TestFlowAlgorithm#getGraphStream()
	 */
	public InputStream getGraphStream() throws IOException {
		return getClass().getResourceAsStream(
				"data/TestFordFulkersonAlgorithm.dgs");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.graphstream.algorithm.flow.test.TestFlowAlgorithm#getFlowAlgorithm()
	 */
	public FlowAlgorithm getFlowAlgorithm() {
		return new FordFulkersonAlgorithm();
	}

	@Test
	public void testFindPath() {
		Graph g = new SingleGraph("g");
		FordFulkersonAlgorithm ffa = new FordFulkersonAlgorithm();
		buildGraph(g, ffa);
		ffa.compute();
	}
	protected static void buildGraph(Graph graph, FordFulkersonAlgorithm ffa) {

		//    E----D  AB=1, BC=5, CD=3, DE=2, BE=6, EA=4
		//   /|    |  Cb(A)=4 (NetworkX finds 3.5, by hand I find 4).
		//  / |    |  Cb(B)=2
		// A  |    |  Cb(C)=0
		//  \ |    |  Cb(D)=2
		//   \|    |  Cb(E)=4 (NetworkX finds 3.5, by hand I find 4).
		//    B----C

		Node A = graph.addNode("A");
		Node B = graph.addNode("B");
		Node E = graph.addNode("E");
		Node C = graph.addNode("C");
		Node D = graph.addNode("D");

		graph.addEdge("AB", "A", "B");
		graph.addEdge("BE", "B", "E");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("ED", "E", "D");
		graph.addEdge("CD", "C", "D");
		graph.addEdge("AE", "A", "E");

		ffa.init(graph);

		ffa.setCapacity(A, B, 1);
		ffa.setCapacity(B, E, 6);
		ffa.setCapacity(B, C, 5);
		ffa.setCapacity(E, D, 2);
		ffa.setCapacity(C, D, 3);
		ffa.setCapacity(A, E, 4);

		ffa.setSourceId("A");
		ffa.setSinkId("C");
	}
}
