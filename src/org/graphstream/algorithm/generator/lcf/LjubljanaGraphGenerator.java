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
 * Build a Ljubljana graph.
 * 
 * <dl>
 * <dt>Nodes</dt>
 * <dd>112</dd>
 * <dt>LCF</dt>
 * <dd>[47, -23, -31, 39, 25, -21, -31, -41, 25, 15, 29, -41, -19, 15, -49, 33,
 * 39, -35, -21, 17, -33, 49, 41, 31, -15, -29, 41, 31, -15, -25, 21, 31, -51,
 * -25, 23, 9, -17, 51, 35, -29, 21, -51, -39, 33, -9, -51, 51, -47, -33, 19,
 * 51, -21, 29, 21, -31, -39]^2</dd>
 * </dl>
 * 
 * @reference Weisstein, Eric W. "Ljubljana Graph." From MathWorld--A Wolfram
 *            Web Resource. http://mathworld.wolfram.com/LjubljanaGraph.html
 * 
 */
public class LjubljanaGraphGenerator extends LCFGenerator {

	public LjubljanaGraphGenerator() {
		super(new LCF(2, 47, -23, -31, 39,
				25, -21, -31, -41, 25, 15, 29, -41, -19, 15, -49, 33, 39, -35, -21,
				17, -33, 49, 41, 31, -15, -29, 41, 31, -15, -25, 21, 31, -51, -25,
				23, 9, -17, 51, 35, -29, 21, -51, -39, 33, -9, -51, 51, -47, -33,
				19, 51, -21, 29, 21, -31, -39), 112, false);
	}
}
