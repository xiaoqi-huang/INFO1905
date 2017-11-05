import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import textbook.AdjacencyMapGraph;
import textbook.Graph;
import textbook.Vertex;

public class TravelDestinationsTest {

	private TravelDestinations createTravelDestinations() {
		Graph<String, Integer> graph = new AdjacencyMapGraph<String, Integer>(true);
		Vertex<String> au = graph.insertVertex("Australia");
		Vertex<String> us = graph.insertVertex("USA");
		Vertex<String> uk = graph.insertVertex("UK");
		Vertex<String> fr = graph.insertVertex("France");
		Vertex<String> de = graph.insertVertex("Germany");
		graph.insertEdge(au, us, null);
		graph.insertEdge(au, uk, null);
		graph.insertEdge(uk, fr, null);
		graph.insertEdge(uk, au, null);
		graph.insertEdge(us, fr, null);
		graph.insertEdge(us, de, null);
		TravelDestinations td = new TravelDestinations(graph);
		return td;
	}

	@Test
	public void testGetDirectDestinations() {
		TravelDestinations td = createTravelDestinations();

		List<String> expected = Arrays.asList("USA", "UK");
		List<String> actual = td.getDirectDestinations("Australia");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList("France", "Germany");
		actual = td.getDirectDestinations("USA");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList("France", "Australia");
		actual = td.getDirectDestinations("UK");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList();
		actual = td.getDirectDestinations("France");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList();
		actual = td.getDirectDestinations("Germany");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testIsDirectFlight() {
		TravelDestinations td = createTravelDestinations();

		assertTrue(td.isDirectFlight("Australia", "USA"));
		assertTrue(td.isDirectFlight("Australia", "UK"));
		assertTrue(td.isDirectFlight("UK", "France"));
		assertTrue(td.isDirectFlight("UK", "Australia"));
		assertTrue(td.isDirectFlight("USA", "France"));
		assertTrue(td.isDirectFlight("USA", "Germany"));

		assertFalse(td.isDirectFlight("Australia", "France"));
		assertFalse(td.isDirectFlight("Australia", "Germany"));
		assertFalse(td.isDirectFlight("USA", "UK"));
		assertFalse(td.isDirectFlight("UK", "USA"));
		assertFalse(td.isDirectFlight("UK", "Germany"));
		assertFalse(td.isDirectFlight("France", "Australia"));
		assertFalse(td.isDirectFlight("France", "Germany"));
		assertFalse(td.isDirectFlight("Germany", "Australia"));
		assertFalse(td.isDirectFlight("Germany", "UK"));
		assertFalse(td.isDirectFlight("Germany", "France"));

		assertFalse(td.isDirectFlight("USA", "Australia"));
		assertFalse(td.isDirectFlight("France", "UK"));
		assertFalse(td.isDirectFlight("France", "USA"));
		assertFalse(td.isDirectFlight("Germany", "USA"));

		assertFalse(td.isDirectFlight("Australia", "Australia"));
		assertFalse(td.isDirectFlight("USA", "USA"));
		assertFalse(td.isDirectFlight("UK", "UK"));
		assertFalse(td.isDirectFlight("France", "France"));
		assertFalse(td.isDirectFlight("Germany", "Germany"));
	}

	@Test
	public void testGetReachableDestinations() {
		TravelDestinations td = createTravelDestinations();

		List<String> expected = Arrays.asList();
		List<String> actual = td.getReachableDestinations("New Zealand");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList("USA", "UK", "France", "Germany");
		actual = td.getReachableDestinations("Australia");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList("France", "Germany");
		actual = td.getReachableDestinations("USA");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList("Australia", "USA", "France", "Germany");
		actual = td.getReachableDestinations("UK");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList();
		actual = td.getReachableDestinations("France");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		expected = Arrays.asList();
		actual = td.getReachableDestinations("Germany");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testCloserDestination() {
		TravelDestinations td = createTravelDestinations();
		// Australia --> USA/France
		assertEquals("USA", td.closerDestination("Australia", "USA", "France"));
		// Australia --> USA/UK
		// tie
		String result = td.closerDestination("Australia", "USA", "UK");
		assertTrue(result.equals("USA") || result.equals("UK"));
		// USA --> Australia/Germany
		// One destination is not reachable
		assertEquals("Germany", td.closerDestination("USA", "Australia", "Germany"));
		// USA --> UK/Australia
		// Both destinations are not reachable
		assertNull(td.closerDestination("USA", "UK", "Australia"));
		// Country A --> Country B/Country C
		// countries do not exist
		assertNull(td.closerDestination("Country A", "Country B", "Country C"));
	}

	@Test
	public void testShortestPathCost() {
		Graph<String, Integer> graph = new AdjacencyMapGraph<String, Integer>(true);
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		graph.insertEdge(a, b, 1);
		TravelDestinations td = new TravelDestinations(graph);

		assertEquals(1, td.shortestPathCost("A", "B"));
		assertEquals(Integer.MAX_VALUE, td.shortestPathCost("B", "A"));

		graph = new AdjacencyMapGraph<String, Integer>(true);
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");
		Vertex<String> d = graph.insertVertex("D");
		Vertex<String> e = graph.insertVertex("E");
		graph.insertEdge(a, b, 2);
		graph.insertEdge(a, c, 5);
		graph.insertEdge(b, d, 3);
		graph.insertEdge(c, d, 6);
		graph.insertEdge(d, e, 9);
		td = new TravelDestinations(graph);

		assertEquals(14, td.shortestPathCost("A", "E"));
	}

	@Test
	public void testShortestPath() {
		Graph<String, Integer> graph = new AdjacencyMapGraph<String, Integer>(true);
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		graph.insertEdge(a, b, 1);
		TravelDestinations td = new TravelDestinations(graph);

		List<String> expected = Arrays.asList("A", "B");
		List<String> actual = td.shortestPath("A", "B");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		assertNull(td.shortestPath("B", "A"));

		graph = new AdjacencyMapGraph<String, Integer>(true);
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");
		Vertex<String> d = graph.insertVertex("D");
		Vertex<String> e = graph.insertVertex("E");
		graph.insertEdge(a, b, 2);
		graph.insertEdge(a, c, 5);
		graph.insertEdge(b, d, 3);
		graph.insertEdge(c, d, 6);
		graph.insertEdge(d, e, 9);
		td = new TravelDestinations(graph);

		expected = Arrays.asList("A", "B", "D", "E");
		actual = td.shortestPath("A", "E");
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testShortestPathCostFloydWarshall() {
		Graph<String, Integer> graph = new AdjacencyMapGraph<String, Integer>(true);
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		graph.insertEdge(a, b, 1);
		TravelDestinations td = new TravelDestinations(graph);

		assertEquals(1, td.shortestPathCostFloydWarshall("A", "B"));
		assertEquals(Integer.MAX_VALUE, td.shortestPathCostFloydWarshall("B", "A"));

		graph = new AdjacencyMapGraph<String, Integer>(true);
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");
		Vertex<String> d = graph.insertVertex("D");
		Vertex<String> e = graph.insertVertex("E");
		graph.insertEdge(a, b, 2);
		graph.insertEdge(a, c, 5);
		graph.insertEdge(b, d, 3);
		graph.insertEdge(c, d, 6);
		graph.insertEdge(d, e, 9);
		td = new TravelDestinations(graph);

		assertEquals(14, td.shortestPathCostFloydWarshall("A", "E"));
	}
}
