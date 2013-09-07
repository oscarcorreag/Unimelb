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

//		HashSet<Integer> vertices_set = g.readTestVertices("test_" + dataset + ".txt");
		ArrayList<Edge> edgestest = g.getEdgesFromFile("test_" + dataset + ".txt","\t");
		ArrayList<Edge> edgestrain = g.getEdgesFromFile("out_train.txt",",");
		
		edgestest.addAll(edgestrain);
//		HashSet vertices_set = edges
		System.out.println(edgestest.size());
		g.readTrainGraphFromFile(edgestest, "train_" + dataset + "_mod.txt");

//		ArrayList<Edge> edges = g.getTestEdges("test_" + dataset + "_from_678_1200.txt");
//		g.writeFiles("out_train.txt", "out_test.txt", edges0);
		
		g.createFileWithMeasures("out_train.txt");
		g.createFileWithMeasures("out_test.txt");
		
		
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