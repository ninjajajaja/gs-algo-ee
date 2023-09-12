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
 * @since 2015-12-15
 * 
 * @author Guilhelm Savin <guilhelm.savin@graphstream-project.org>
 */
package org.graphstream.algorithm.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashSet;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.ConnectedComponents.ConnectedComponent;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;
import org.junit.Assert;
import org.junit.Test;

public class TestConnectedComponent {

	@Test
	public void testBasic() {
		Graph g = new DefaultGraph("g");
		load(g, "data/cc-basic.dgs", true);

		ConnectedComponents cc = new ConnectedComponents();

		cc.init(g);
		cc.compute();

		check(cc, createCC("A", "B", "C"), createCC("D", "E", "F"));

		moreTests(cc);
	}

	public void moreTests(ConnectedComponents cc) {

		cc.setCountAttribute("count");

		// publish 373
		cc.publish();

		// getGiantComponent 390
		cc.getGiantComponent();

		// getConnectedComponentsCount 454
		cc.getConnectedComponentsCount(10, 10);

		HashSet<ConnectedComponent> ccSet = cc.getComponents();

		// nodes 787
		ccSet.forEach(c -> assertNotNull(c.nodes()));

		// getNodeSet 802
		ccSet.forEach(c -> assertNotNull(c.getNodeSet()));

		// edges 818, 819
		ccSet.forEach(c -> assertNotNull(c.edges()));

		// contains 832
		ccSet.forEach(c -> assertTrue(c.contains(c.nodes().findFirst().get())));

	}

	@Test
	public void testEdgeDynamics() {
		Graph g = new DefaultGraph("g");

		load(g, "data/cc-basic.dgs", true);
		FileSourceDGS dgs = load(g, "data/cc-edge-dynamics.dgs", false);

		ConnectedComponents cc = new ConnectedComponents();

		cc.init(g);
		cc.compute();

		try {
			//
			// The two connected components wil be joined by an edge in this
			// next step.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("A", "B", "C", "D", "E", "F"));

		try {
			//
			// Now we delete AB, AC, BC, DE, DF and we add CD.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("A", "D", "C"), createCC("E", "F"), createCC("B"));

		moreTests(cc);
	}

	@Test
	public void testNodeDynamics() {
		Graph g = new DefaultGraph("g");

		load(g, "data/cc-basic.dgs", true);

		ConnectedComponents cc = new ConnectedComponents();

		cc.init(g);
		cc.compute();

		FileSourceDGS dgs = load(g, "data/cc-node-dynamics.dgs", false);

		try {
			//
			// We add a new node, linked to no one else.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("A", "B", "C"), createCC("D", "E", "F"), createCC("G"));

		try {
			//
			// We link G to A.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("A", "B", "C", "G"), createCC("D", "E", "F"));

		try {
			//
			// We remove A.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("B", "C"), createCC("D", "E", "F"), createCC("G"));

		moreTests(cc);
	}

	@Test
	public void testEdgeCut() {
		Graph g = new DefaultGraph("g");

		load(g, "data/cc-edge-cut.dgs", true);

		ConnectedComponents cc = new ConnectedComponents();

		cc.init(g);
		cc.setCutAttribute("cut");
		cc.compute();

		check(cc, createCC("A", "B", "C"), createCC("D", "E", "F"));

		moreTests(cc);
	}
	
	@Test
	public void testEdgeCutDynamics() {
		Graph g = new DefaultGraph("g");

		load(g, "data/cc-edge-cut.dgs", true);

		ConnectedComponents cc = new ConnectedComponents();

		cc.init(g);
		cc.setCutAttribute("cut");
		cc.compute();

		FileSourceDGS dgs = load(g, "data/cc-edge-cut-dynamics.dgs", false);

		try {
			//
			// AD will not be a "but edge" anymore.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("A", "B", "C", "D", "E", "F"));

		try {
			//
			// AD will be a cut edge.
			//
			dgs.nextStep();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		check(cc, createCC("A", "B", "C"), createCC("D", "E", "F"));

		moreTests(cc);
	}

	static FileSourceDGS load(Graph g, String dgsPath, boolean all) {
		FileSourceDGS dgs = new FileSourceDGS();
		InputStream in = TestConnectedComponent.class.getResourceAsStream(dgsPath);

		dgs.addSink(g);

		if (all) {
			try {
				dgs.readAll(in);
			} catch (IOException e) {
				Assert.fail(e.getMessage());
			}
		} else {
			try {
				dgs.begin(in);
			} catch (IOException e) {
				Assert.fail(e.getMessage());
			}
		}

		return dgs;
	}

	static String[] createCC(String... nodes) {
		return nodes;
	}

	static void check(ConnectedComponents algo, String[]... ccs) {
		Assert.assertEquals(ccs.length, algo.getConnectedComponentsCount());

		for (int i = 0; i < ccs.length; i++) {
			for (int j = 1; j < ccs[i].length; j++) {
				ConnectedComponent cc1 = algo.getConnectedComponentOf(ccs[i][0]);
				ConnectedComponent cc2 = algo.getConnectedComponentOf(ccs[i][j]);

				Assert.assertEquals(cc1, cc2);
			}

			for (int j = i + 1; j < ccs.length; j++) {
				ConnectedComponent cc1 = algo.getConnectedComponentOf(ccs[i][0]);
				ConnectedComponent cc2 = algo.getConnectedComponentOf(ccs[j][0]);

				Assert.assertNotEquals(cc1, cc2);
			}
		}
	}
}
