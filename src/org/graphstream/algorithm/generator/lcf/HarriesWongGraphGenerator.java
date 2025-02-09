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
 * @since 2009-02-19
 * 
 * @author Guilhelm Savin <guilhelm.savin@graphstream-project.org>
 */
package org.graphstream.algorithm.generator.lcf;

import org.graphstream.algorithm.generator.LCFGenerator;

/**
 * Build a Harries-Wong graph.
 * 
 * <dl>
 * <dt>Nodes</dt>
 * <dd>70</dd>
 * <dt>LCF</dt>
 * <dd>[9, 25, 31, -17, 17, 33, 9, -29, -15, -9, 9, 25, -25, 29, 17, -9, 9, -27,
 * 35, -9, 9, -17, 21, 27, -29, -9, -25, 13, 19, -9, -33, -17, 19, -31, 27, 11,
 * -25, 29, -33, 13, -13, 21, -29, -21, 25, 9, -11, -19, 29, 9, -27, -19, -13,
 * -35, -9, 9, 17, 25, -9, 9, 27, -27, -21, 15, -9, 29, -29, 33, -9, -25]</dd>
 * </dl>
 * 
 * @reference Weisstein, Eric W. "Harries-Wong Graph." From MathWorld--A Wolfram
 *            Web Resource. http://mathworld.wolfram.com/Harries-WongGraph.html
 * 
 */
public class HarriesWongGraphGenerator extends LCFGenerator {

	public HarriesWongGraphGenerator() {
		super(new LCF(1, 9, 25, 31, -17,
				17, 33, 9, -29, -15, -9, 9, 25, -25, 29, 17, -9, 9, -27, 35, -9, 9,
				-17, 21, 27, -29, -9, -25, 13, 19, -9, -33, -17, 19, -31, 27, 11,
				-25, 29, -33, 13, -13, 21, -29, -21, 25, 9, -11, -19, 29, 9, -27,
				-19, -13, -35, -9, 9, 17, 25, -9, 9, 27, -27, -21, 15, -9, 29, -29,
				33, -9, -25), 70, false);
	}
}
