package test;

import java.util.HashSet;

import managers.GraphManager;

import org.junit.Test;

public class GraphManagerTest {

	
	//@Test
	/*public void testReadFromFile() {
		AdjList g = new AdjList();
        g.readTrainData();
		assertNotNull(g);
	}*/
	
	@Test
	public void testCalcCN() {
		GraphManager g = new GraphManager();
		HashSet<Integer> vertices_set = g.readTestVertices();
		
		g.readTrainGraphFromFile(vertices_set); 
		g.writeTrainFile(vertices_set);
		g.writeTestFile();
	}
	
}
