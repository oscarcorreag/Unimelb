package test;

import java.util.HashSet;

import managers.GraphManager;

import org.junit.Test;

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
		System.out.println(vertices_set.size());
		g.readTrainGraphFromFile(vertices_set, "train_final_mod.txt");
//		g.readTrainGraphFromFile(vertices_set, "train_final.txt");
//		g.writeTrainFile(vertices_set, "train_set.txt");
		System.out.println("Write test file");
		g.writeTestFile("test_final.txt", "test_set.txt");
	}

}
