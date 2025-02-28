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
package org.graphstream.algorithm.generator.demo;

import org.graphstream.algorithm.generator.LifeGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.graphstream.ui.view.camera.Camera;
import org.graphstream.ui.view.Viewer;

public class LifeGeneratorDemo {

	public static void main(String... args) throws Exception {
		try {
			Class.forName("org.graphstream.ui.swing.util.Display");

			System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
		} catch (ClassNotFoundException e) {
		}

		LifeGenerator gen = new LifeGenerator(
				LifeGeneratorDemo.class.getResourceAsStream("life-demo.png"));

		Graph g = new AdjacencyListGraph("g");
		gen.addSink(g);
		gen.tore = false;

		g.setAttribute("ui.quality");
		g.setAttribute("ui.antialias");

		Viewer v = g.display(false);
		Camera cam = v.getDefaultView().getCamera();
		cam.setViewCenter(gen.width / 2.0, gen.height / 2.0, 0.0);
		cam.setViewPercent(1.1);

		gen.begin();

		while (gen.nextEvents())
			Thread.sleep(50);
	}

}
