package test;

import java.util.ArrayList;
import java.util.HashSet;

import managers.GraphManager;

import org.junit.Test;

import dto.Edge;

public class GraphManagerTest {

	// @Test
	/*
	 * public void testReadFromFile() { AdjList g = new AdjList();
	 * g.readTrainData(); assertNotNull(g); }
	 */

	@Test
	public void testCalcCN() {
        String dataset = "final";
		GraphManager g = new GraphManager();

		HashSet<Integer> vertices_set = g.readTestVertices("test_" + dataset + ".txt");
		g.readTrainGraphFromFile(vertices_set, "train_" + dataset + "_mod.txt");
		
		ArrayList<Edge> edges = g.getTestEdges("test_" + dataset + ".txt");
		g.writeFiles("out_train.txt", "out_test.txt", edges);

	}

}
