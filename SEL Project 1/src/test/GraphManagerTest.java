package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
		System.out.println("done reading g : " );

		ArrayList<Edge> edges = g.getTestEdges("test_" + dataset + "_1001_1500.txt");
		g.writeFiles("out_train.txt", "out_test.txt", edges);
//		HashMap<Integer, Double> initProbs  = new HashMap<Integer, Double> ();
////int src = 2685339;
//int src = 355230;
//int dest = 2748795;
//		initProbs.put(src, 1.0);
//		
//		HashMap<Integer, Double> pr_Probs  = new HashMap<Integer, Double> ();
//		pr_Probs.put(dest, 0.0);
//		pr_Probs.put(src, 1.0);
//		long t0 = System.currentTimeMillis();
//		 Map<Integer, Double> pr_Probs_1	= g._graph.calcPageRank(src, initProbs, pr_Probs);
//			System.out.println("new : " + pr_Probs_1.get(dest))	;
//long t1 = System.currentTimeMillis();
//System.out.println(t1- t0);
//		  pr_Probs_1	= g._graph.calcPageRankOld(src, initProbs);
//		  System.out.println(System.currentTimeMillis()- t1 );
//			System.out.println("old : " + pr_Probs_1.get(dest))	;
	}

}