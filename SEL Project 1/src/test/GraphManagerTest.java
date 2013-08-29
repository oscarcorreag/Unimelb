package test;

import static org.junit.Assert.assertNotNull;
import managers.AdjList;

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
		AdjList g = new AdjList();
		g.readTrainData();
        g.calculateMeasures();		
	}
	
}
