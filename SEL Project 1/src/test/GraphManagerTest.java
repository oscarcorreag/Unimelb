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

		GraphManager g = new GraphManager();

		HashSet<Integer> vertices_set = g.readTestVertices("test_final.txt");
		g.readTrainGraphFromFile(vertices_set, "train_final_mod.txt");
		
		ArrayList<Edge> edges = g.getTestEdges("test_final.txt");
		g.writeFiles("out_train.txt", "out_test.txt", edges);

	}

}
