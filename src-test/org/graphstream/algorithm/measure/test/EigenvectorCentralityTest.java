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
 * @author Hicham Brahimi <hicham.brahimi@graphstream-project.org>
 */
package org.graphstream.algorithm.measure.test;

import static org.junit.Assert.assertEquals;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.measure.AbstractCentrality.NormalizationMode;
import org.graphstream.algorithm.measure.EigenvectorCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.junit.Test;

public class EigenvectorCentralityTest {

	@Test
	public void testGeneration() {

		Graph g = new AdjacencyListGraph("g");

		BarabasiAlbertGenerator gen = new BarabasiAlbertGenerator();
		gen.addSink(g);
		gen.begin();
		for (int i = 0; i < 1000; i++)
			gen.nextEvents();
		gen.end();

		EigenvectorCentrality eigenvectorCentrality = new EigenvectorCentrality("ui.color", NormalizationMode.MAX_1_MIN_0);
		eigenvectorCentrality.init(g);
		eigenvectorCentrality.compute();

		eigenvectorCentrality.setNormalizationMode(NormalizationMode.SUM_IS_1);
		assertEquals(NormalizationMode.SUM_IS_1, eigenvectorCentrality.getNormalizationMode());
		eigenvectorCentrality.init(g);
		eigenvectorCentrality.compute();

		eigenvectorCentrality.centralityAttribute = "ca";
		assertEquals("ca", eigenvectorCentrality.centralityAttribute);
	}

}
