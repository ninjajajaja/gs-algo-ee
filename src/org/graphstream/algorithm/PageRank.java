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
 * @since 2012-07-12
 * 
 * @author Stefan Balev <stefan.balev@graphstream-project.org>
 * @author Guilhelm Savin <guilhelm.savin@graphstream-project.org>
 * @author Hicham Brahimi <hicham.brahimi@graphstream-project.org>
 */
package org.graphstream.algorithm;

import gnu.trove.list.array.TDoubleArrayList;

import org.graphstream.algorithm.util.Parameter;
import org.graphstream.algorithm.util.Result;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.ElementSink;

/**
 * <p>
 * The PageRank is an algorithm that measures the "importance" of the nodes in a
 * graph. It assigns to each node a rank. This rank corresponds to the
 * probability that a "random surfer" visits the node. The surfer goes from node
 * to node in the following way: with probability <it>d</it> she chooses a
 * random outgoing arc and with probability <it>1 - d</it> she "teleports" to a
 * random node (possibly not connected to the current). The probability
 * <it>d</it> is called damping factor. By default it is 0.85 but it can be
 * customized (see {@link #setDampingFactor(double)}). The ranks are real
 * numbers between 0 and 1 and sum up to one.
 * </p>
 * 
 * <h2>Usage</h2>
 * 
 * <p>
 * This implementation uses a variant of the power iteration algorithm to
 * compute the node ranks. It computes the approximate ranks iteratively going
 * closer to the exact values at each iteration. The accuracy can be controlled
 * by a precision parameter (see {@link #setPrecision(double)}). When the L1
 * norm of the difference between two consecutive rank vectors becomes less than
 * this parameter, the result is considered precise enough and the computation
 * stops.
 * </p>
 * 
 * <p>
 * This implementation works with both directed and undirected edges. An
 * undirected edge acts as two directed arcs.
 * </p>
 * 
 * <p>
 * The graph dynamics is taken into account and the ranks are not computed from
 * scratch at each modification in the structure of the graph. However, the
 * ranks become less and less accurate after each modification. To establish the
 * desired precision, one must either explicitly call {@link #compute()} or ask
 * for a rank of a node by calling {@link #getRank(Node)}.
 * </p>
 * 
 * <p>
 * The computed ranks are stored in node attribute. The name of this attribute
 * can be changed by a call to {@link #setRankAttribute(String)} but only before
 * the call to {@link #init(Graph)}. Another way to obtain the ranks is to call
 * {@link #getRank(Node)}. The second method is preferable because it will
 * update the ranks if needed and will always return values within the desired
 * precision.
 * </p>
 * 
 * <h2>Example</h2>
 * 
 * <pre>
 * Graph graph = new SingleGraph(&quot;test&quot;);
 * graph.addAttribute(&quot;ui.antialias&quot;, true);
 * graph.addAttribute(&quot;ui.stylesheet&quot;,
 * 		&quot;node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}&quot;);
 * graph.display();
 * 
 * DorogovtsevMendesGenerator generator = new DorogovtsevMendesGenerator();
 * generator.setDirectedEdges(true, true);
 * generator.addSink(graph);
 * 
 * PageRank pageRank = new PageRank();
 * pageRank.init(graph);
 * 
 * generator.begin();
 * while (graph.getNodeCount() &lt; 100) {
 * 	generator.nextEvents();
 * 	for (Node node : graph) {
 * 		double rank = pageRank.getRank(node);
 * 		node.addAttribute(&quot;ui.size&quot;,
 * 				5 + Math.sqrt(graph.getNodeCount() * rank * 20));
 * 		node.addAttribute(&quot;ui.label&quot;, String.format(&quot;%.2f%%&quot;, rank * 100));
 * 	}
 * 	Thread.sleep(1000);
 * }
 * </pre>
 * 
 * @complexity Each iteration takes O(m + n) time, where n is the number of
 *             nodes and m is the number of edges. The number of iterations
 *             needed to converge depends on the desired precision.
 * 
 * @reference Lawrence Page, Sergey Brin, Rajeev Motwani and Terry Winograd. The
 *            PageRank citation ranking: Bringing order to the Web. 1999
 * 
 * 
 */
public class PageRank implements DynamicAlgorithm, ElementSink {
	/**
	 * Default damping factor
	 */
	public static final double DEFAULT_DAMPING_FACTOR = 0.85;

	/**
	 * Default precision
	 */
	public static final double DEFAULT_PRECISION = 1.0e-5;

	/**
	 * Default rank attribute
	 */
	public static final String DEFAULT_RANK_ATTRIBUTE = "PageRank";

	/**
	 * Current damping factor
	 */
	public double dampingFactor;

	/**
	 * Current numeric precision
	 */
	public double precision;

	/**
	 * Current rank attribute
	 */
	public String rankAttribute;

	/**
	 * Our graph
	 */
	protected Graph graph;

	/**
	 * Am I up to date ?
	 */
	protected boolean upToDate;

	/**
	 * The L1 norm of the difference between two consecutive rank vectors
	 */
	protected double normDiff;

	/**
	 * Used to temporary store the new ranks during an iteration
	 */
	protected TDoubleArrayList newRanks;

	/**
	 * total iteration count
	 */
	public int iterationCount;

	/**
	 * Verbose mode
	 */
	public boolean verbose;

	private int graphNodeCount;

	/**
	 * Creates a new instance.
	 * 
	 * The damping factor, the precision and the rank attribute are set to their
	 * default values
	 */
	public PageRank() {
		this(DEFAULT_DAMPING_FACTOR, DEFAULT_PRECISION, DEFAULT_RANK_ATTRIBUTE);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param dampingFactor
	 *            Damping factor
	 * @param precision
	 *            Numeric precision
	 * @param rankAttribute
	 *            Rank attribute
	 */
	public PageRank(double dampingFactor, double precision, String rankAttribute) {
		setDampingFactor(dampingFactor);
		setPrecision(precision);
		setRankAttribute(rankAttribute);
		verbose = false;
	}

	// parameters

	/**
	 * Sets the damping factor.
	 * 
	 * @param dampingFactor
	 *            The new damping factor
	 * @throws IllegalArgumentException
	 *             If the damping factor is less than 0.01 or greater than 0.99
	 */
	@Parameter
	public void setDampingFactor(double dampingFactor)
			throws IllegalArgumentException {
		if (dampingFactor < 0.01 || dampingFactor > 0.99)
			throw new IllegalArgumentException(
					"The damping factor must be between 0.01 and 0.99");
		this.dampingFactor = dampingFactor;
		upToDate = false;
	}

	/**
	 * Sets the numeric precision. Precision values close to zero lead to more
	 * accurate results, but slower convergence
	 * 
	 * @param precision
	 *            The new precision
	 * @throws IllegalArgumentException
	 *             if the precision is less than 1.0e-7
	 */
	@Parameter
	public void setPrecision(double precision) throws IllegalArgumentException {
		if (precision < 1.0e-7)
			throw new IllegalArgumentException("Precision is too small");
		this.precision = precision;
		upToDate = false;
	}

	/**
	 * Sets the rank attribute.
	 * 
	 * The computed ranks of each node are stored as values of this attribute.
	 * 
	 * @param rankAttribute
	 *            The node attribute used to store the computed ranks
	 * @throws IllegalStateException
	 *             if the algorithm is already initialized
	 */
	@Parameter
	public void setRankAttribute(String rankAttribute)
			throws IllegalStateException {
		if (graph != null)
			throw new IllegalStateException(
					"this method can be called only before init");
		this.rankAttribute = rankAttribute;
	}

	// DynamicAlgorithm implementation

	public void init(Graph graph) {
		this.graph = graph;
		graph.addElementSink(this);
		graphNodeCount = graph.getNodeCount();
		double initialRank = 1.0 / graphNodeCount;
		
		graph.nodes().forEach(node -> node.setAttribute(rankAttribute, initialRank));
		
		newRanks = new TDoubleArrayList(graphNodeCount);
		upToDate = false;
		iterationCount = 0;
	}

	public void compute() {
		if (upToDate)
			return;
		do {
			iteration();
			if (verbose)
				System.err.printf("%6d%16.8f%n", iterationCount, normDiff);
		} while (normDiff > precision);
		upToDate = true;
	}

	public void terminate() {
		graph.removeElementSink(this);
		newRanks.clear();
		newRanks = null;
		graph = null;
	}

	@Result
	public String defaultResult() {
		graph.nodes().forEach(node -> {
			double rank = getRank(node);
			node.setAttribute("ui.size", 5 + Math.sqrt(graphNodeCount * rank * 20));
			node.setAttribute("ui.label", String.format("%.2f%%", rank * 100));
		});
		
		return "ui.size and ui.label changed";
	}
	// ElementSink implementation

	public void nodeAdded(String sourceId, long timeId, String nodeId) {
		// the initial rank of the new node will be 0
		graph.getNode(nodeId).setAttribute(rankAttribute,
				graphNodeCount == 1 ? 1.0 : 0.0);
		upToDate = false;
	}

	public void nodeRemoved(String sourceId, long timeId, String nodeId) {
		// removed node will give equal parts of its rank to the others
		double part = graph.getNode(nodeId).getNumber(rankAttribute)
				/ (graphNodeCount - 1);
		
		graph.nodes()
			.filter(node -> !node.getId().equals(nodeId))
			.forEach(node -> node.setAttribute(rankAttribute, node.getNumber(rankAttribute) + part));
		
		upToDate = false;
	}

	public void edgeAdded(String sourceId, long timeId, String edgeId,
			String fromNodeId, String toNodeId, boolean directed) {
		upToDate = false;
	}

	public void edgeRemoved(String sourceId, long timeId, String edgeId) {
		upToDate = false;
	}

	public void graphCleared(String sourceId, long timeId) {
		upToDate = true;
	}

	public void stepBegins(String sourceId, long timeId, double step) {
	}

	// helpers

	protected void iteration() {
		double dampingTerm = (1 - dampingFactor) / graphNodeCount;
		newRanks.clear();
		double danglingRank = 0;
		for (int i = 0; i < graphNodeCount; i++) {
			Node node = graph.getNode(i);
			double sum = 0;
			int inDegree = node.getInDegree();
			for (int j = 0; j < inDegree; j++) {
				Node other = node.getEnteringEdge(j).getOpposite(node);
				sum += other.getNumber(rankAttribute) / other.getOutDegree();
			}
			newRanks.add(dampingTerm + dampingFactor * sum);
			if (node.getOutDegree() == 0)
				danglingRank += node.getNumber(rankAttribute);
		}
		danglingRank *= dampingFactor / graphNodeCount;

		normDiff = 0;
		for (int i = 0; i < graphNodeCount; i++) {
			Node node = graph.getNode(i);
			double currentRank = node.getNumber(rankAttribute);
			double newRank = newRanks.get(i) + danglingRank;
			normDiff += Math.abs(newRank - currentRank);
			node.setAttribute(rankAttribute, newRank);
		}
		iterationCount++;
	}

	// results

	/**
	 * Returns the rank of a node. If the ranks are not up to date, recomputes
	 * them
	 * 
	 * @param node
	 *            A node
	 * @return The rank of the node
	 */
	public double getRank(Node node) {
		compute();
		return node.getNumber(rankAttribute);
	}
}
