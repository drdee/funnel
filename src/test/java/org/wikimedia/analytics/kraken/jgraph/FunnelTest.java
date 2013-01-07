package org.wikimedia.analytics.kraken.jgraph;

import org.wikimedia.analytics.kraken.exceptions.MalformedFunnelException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Funnel.
 */
public class FunnelTest {

    /** Create the test case. */
	public final String funnelDefinition = "http://en.wikipedia.org/wiki/A,http://en.wikipedia.org/wiki/B\n" +
			"http://en.wikipedia.org/wiki/B, http://en.wikipedia.org/wiki/C\n" +
			"http://en.wikipedia.org/wiki/D, http://en.wikipedia.org/wiki/B\n" +
			"http://en.wikipedia.org/wiki/B, http://en.wikipedia.org/wiki/E\n";
	private Funnel funnel;

	/**
	 * The first non-trivial funnel is:
	 * A ->      C
	 *      B ->
	 * D ->      E
	 * where {A,B,C,D,E} are abstract names for the steps in the funnel.
	 * There are two unique paths in this funnel: {A,B,C} and {D,B,E}
	 */
	public FunnelTest() {
		
		try {
			this.funnel = new Funnel(funnelDefinition);
		} catch (MalformedFunnelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test get destination vertices.
	 */
	@Test
	public final void testGetDestinationVertices() {
		funnel.getDestinationVertices();
		List<URL> endVertices = new ArrayList<URL>();
		try {
			endVertices.add(new URL("http://en.wikipedia.org/C"));
			endVertices.add(new URL("http://en.wikipedia.org/E"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assert funnel.endVertices.containsAll(endVertices);
	}

	@Test
	public final void testGetStartingVertices() {
		List<URL> startVertices = new ArrayList<URL>();
		funnel.getStartingVertices();
		try {
			startVertices.add(new URL("http://en.wikipedia.org/A"));
			startVertices.add(new URL("http://en.wikipedia.org/D"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assert funnel.startVertices.containsAll(startVertices);
	}
	
	@Test
	public final void testIsDag() {
		assert funnel.isDag();
	}

	@Test
	public final void testDetermineUniquePaths() {
		funnel.getStartingVertices();
		funnel.getDestinationVertices();
		funnel.determineUniquePaths();
		System.out.println("Unique paths: " + funnel.paths.size());
		assert (funnel.paths.size() == 2);
	}
	@Test
	public final void testFallOutAnalysis() throws MalformedURLException {
		ArrayList<URL> path0 = new ArrayList<URL>();
		path0.add(new URL("http://en.wikipedia.org/A"));
		path0.add(new URL("http://en.wikipedia.org/B"));
		path0.add(new URL("http://en.wikipedia.org/C"));
		
		ArrayList<URL> path1 = new ArrayList<URL>();
		path1.add(new URL("http://en.wikipedia.org/D"));
		path1.add(new URL("http://en.wikipedia.org/B"));
		path1.add(new URL("http://en.wikipedia.org/E"));
		
		funnel.paths.add(0, path0);
		funnel.paths.add(1, path1);
		
		
		
		HashMap<Integer, Boolean> results = funnel.fallOutAnalysis(funnel.graph);
		assert (results.size() == 2);
		Collection<Boolean> obsValues = results.values();
		Collection<Boolean> testValues = new ArrayList<Boolean>();
		testValues.add(true);
		testValues.add(true);
		assert (obsValues.containsAll(testValues));
	}
	
	@Test
	public final void testAnalysis() {
		//Unfinished test, add code to compare that the results of the two runs
		//are identical.
		funnel.analysis();
		funnel.analysis();
	}
}
