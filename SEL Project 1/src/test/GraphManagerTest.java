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

		HashSet<Integer> vertices_set = g.readTestVertices("test_toy.txt");
//		System.out.println(vertices_set.size());
		g.readTrainGraphFromFile(vertices_set, "train_toy.txt");
//		g.readTrainGraphFromFile(vertices_set, "train_final.txt");
//		g.writeTrainFile(vertices_set, "train_set.txt");
//		System.out.println("Write test file");
// 		g.writeTestFile("test_final.txt", "test_set.txt");
		
		ArrayList<Edge> edges = g.getTestEdges("test_toy.txt");
		g.createThreads(edges, "out_train", "out_test");
		
	}

}
