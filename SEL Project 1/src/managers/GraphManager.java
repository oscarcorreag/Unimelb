package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import dto.Edge;
import dto.Graph;
import dto.Measures;

public class GraphManager {

	public Graph _graph;

	final int MAX = 2;
	final int NUM_ITERATIONS = 2;

	public GraphManager() {
		_graph = new Graph();
	}

	/**
	 * Read the test file given in the contest and returns a unique list with
	 * both followers and followings.
	 * 
	 * @param in_file
	 *            Name of the test file given in the contest.
	 * @return HashSet which contains the ID's of the vertices in the test file.
	 *         This list contains both the source and destination id's.
	 */

	public HashSet<Integer> readTestVertices(String in_file) {

		HashSet<Integer> vertices = new HashSet<Integer>();

		try {
			FileInputStream fstream = new FileInputStream(in_file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");

				Integer srcId_i = Integer.valueOf(ids[0]);
				vertices.add(srcId_i);

				Integer destId_i = Integer.valueOf(ids[1]);
				vertices.add(destId_i);
			}
			in.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return vertices;
	}

	/**
	 * Read the training file which contains the adjacency list and loads the
	 * graph structures: ADJACENCY LIST, INVERTED ADJACENCY LIST AND NEGATIVE
	 * ADJACENCY LIST.
	 * 
	 * @param test_vertices
	 *            List of vertices which are used for determining the INVERTED
	 *            ADJACENCY LIST.
	 * @param in_file
	 *            Name of the file which contains the adjacency list.
	 */
	public void readTrainGraphFromFile(ArrayList<Edge> edges, String in_file) {

		try {
			FileInputStream fstream = new FileInputStream(in_file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			// Loads the graph structure which corresponds to the ORIGINAL
			// ADJACENCY LIST into a map. The key in this map corresponds to the
			// FOLLOWER and the values to the FOLLOWEES.
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");
				Integer srcId_i = Integer.valueOf(ids[0]);

				HashSet<Integer> followees = new HashSet<Integer>();

				for (int i = 1; i < ids.length; i++) {
					Integer desId_i = Integer.valueOf(ids[i]);
					followees.add(desId_i);
				}
				_graph.addVertexAndFollowees(srcId_i, followees);
			}
			in.close();

			
			HashSet<Integer> test_vertices = new HashSet<Integer>();
			for (Edge edge : edges) {
				test_vertices.add(edge.getSourceId());
				test_vertices.add(edge.getDestinationId());
			}
			
			
			
			
			// Get the graph structure just created above in order to create the
			// the NEGATIVE and INVERTED ADJACENCY LISTS.
			Map<Integer, HashSet<Integer>> vertexAndFollowees = _graph.getVertexAndFollowees();
			// List<Integer> followersKeys = new
			// ArrayList<Integer>(vertexAndFollowees.keySet());

//			Random random = new Random();

			Iterator<Entry<Integer, HashSet<Integer>>> it = vertexAndFollowees.entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry<Integer, HashSet<Integer>> vAndF = (Map.Entry<Integer, HashSet<Integer>>) it.next();

				// Get the follower and followings.
				Integer follower = vAndF.getKey();
				HashSet<Integer> followees = vAndF.getValue();

				// Loads the graph structure which corresponds to the NEGATIVE
				// ADJACENCY LIST into a map. The key in this map corresponds to
				// a RANDOM FOLLOWER taken from the ORIGINAL ADJACENCY LIST and
				// the value corresponds to OTHER VERTICES WHICH ARE NOT
				// FOLLOWED by this random one. The number of them is determined
				// by a constant.
				
				
				//------------------ UNCOMMENT THIS TO GET NEGATIVE TRAIN EDGES
				/*
				HashSet<Integer> notFollowees = new HashSet<Integer>();

				for (int i = 0; i < MAX ;) {
					// Integer randomKey =
					// followersKeys.get(random.nextInt(followersKeys.size()));
					Edge randomEdge = edges.get(random.nextInt(edges.size()));
					Integer randomKey = randomEdge.getDestinationId();

					if (follower != randomKey && follower != randomEdge.getSourceId() && !followees.contains(randomKey)) {
						notFollowees.add(randomKey);
						i++;
					}
				}
				_graph.addVertexAndNotFollowees(follower, notFollowees);
*/
				
				
				
				// Loads the graph structure which corresponds to the INVERTED
				// ADJACENCY LIST into a map. The key in this map corresponds to
				// the FOLLOWEE and the values to the FOLLOWERS. However, NOT
				// ALL FOLLOWEES are included, only those which FOLLOW someone
				// or ARE PRESENT in the TEST set.
				for (Integer followee : followees)
//					if (vertexAndFollowees.containsKey(followee) || test_vertices.contains(followee))
					if (test_vertices.contains(followee))
						_graph.addVertexAndFollower(followee, follower);
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Read the test file given in the contest and returns the list of the
	 * edges.
	 * 
	 * @param test_file
	 *            Name of the test file given in the contest.
	 * @return List of edges.
	 */
	public ArrayList<Edge> getEdgesFromFile(String test_file, String delimiter) {

		ArrayList<Edge> edges = new ArrayList<Edge>();

		try {
			FileInputStream fstream = new FileInputStream(test_file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split(delimiter);

				Integer srcId_i = Integer.valueOf(ids[0]);
				Integer destId_i = Integer.valueOf(ids[1]);

				Edge edge = new Edge(srcId_i, destId_i);

				edges.add(edge);
			}
			in.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return edges;
	}

	/**
	 * Write TRAIN and TEST files. These files include measures for each edge
	 * which are used in a supervised learner.
	 * 
	 * @param out_train
	 * @param out_test
	 * @param edges
	 */
	public void writeFiles(String out_train, String out_test, ArrayList<Edge> edges) {

		File trainFile = new File(out_train);
		File testFile = new File(out_test);

		try {
			if (!trainFile.exists())
				trainFile.createNewFile();

			if (!testFile.exists())
				testFile.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter writerTrain = null;
		PrintWriter writerTest = null;

		long t0 = System.currentTimeMillis();
		int counter = 0;
		try {
			writerTrain = new PrintWriter(new BufferedWriter(new FileWriter(out_train)));
			writerTest = new PrintWriter(new BufferedWriter(new FileWriter(out_test)));

			// Map<Integer, HashSet<Integer>> vertexAndFollowees =
			// _graph.getVertexAndFollowees();
			for (Edge edge : edges) {

				if (counter++ % 200 == 0) {
					System.out.println(System.currentTimeMillis() - t0);
				}

				Integer src_id = edge.getSourceId();

				// If the source node appears as a FOLLOWER in the ORIGINAL
				// ADJACENCY LIST, but the destination node IS NOT FOLLOWED by
				// the former; create an EMPTY Measures object. Assumption: the
				// FOLLOWERS in the ORIGINAL ADJACENCY LIST have been completely
				// crawled.
				// HashSet<Integer> followees = vertexAndFollowees.get(src_id);
				//
				// if (followees != null &&
				// !followees.contains(edge.getDestinationId())) {
				// writerTest.write(new Measures().toString() + ",?\n");
				// } else {

				// Calculate the page rank for the current source node. This
				// is a map which contains the probabilities for this node
				// and its neighbors.

				Set<Integer> posDestIds = _graph.getRandomPositiveEdges(src_id, MAX);
				Set<Integer> negDestIds = _graph.getRandomNegativeEdges(src_id);
				Map<Integer, Double> initPR = new HashMap<Integer, Double>();
				initPR.put(src_id, 1.0);

				Map<Integer, Double> initProbs = new HashMap<Integer, Double>();
				initProbs.put(src_id, 1.0);

				for (Integer node : posDestIds) {
					initProbs.put(node, 0.0);
				}
				for (Integer x : negDestIds) {
					initProbs.put(x, 0.0);
				}

				int test_dest = edge.getDestinationId();
				initProbs.put(test_dest, 0.0);

				Map<Integer, Double> pageRankProbs = _graph.calcPageRank(src_id, initPR, initProbs);

				// Get random POSITIVE edges for TRAINING SET, calculate
				// their
				// measures and write to the file.
				for (Integer dest_id : posDestIds) {
					// Measures m = _graph.calculateMeasures(src_id, dest_id,
					// pageRankProbs);
					// writerTrain.write(m.toString() + ",1\n");
					writerTrain.write(src_id + "," + dest_id + "," + pageRankProbs.get(dest_id) + ",1\n");
				}

				// Get random NEGATIVE edges for TRAINING SET, calculate
				// their
				// measures and write to the file.
				if (negDestIds != null) {
					for (Integer dest_id : negDestIds) {
						// Measures m = _graph.calculateMeasures(src_id,
						// dest_id, pageRankProbs);
						// writerTrain.write(m.toString() + ",-1\n");
						writerTrain.write(src_id + "," + dest_id + "," + pageRankProbs.get(dest_id) + ",-1\n");
					}
				}
				// Calculate measures for TEST instances and write to the
				// file.
				// Measures m = _graph.calculateMeasures(src_id,
				// edge.getDestinationId(), pageRankProbs);
				// writerTest.write(m.toString() + ",?\n");
				writerTest.write(src_id + "," + test_dest + "," + pageRankProbs.get(test_dest) + ",?\n");

			}
			// }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writerTrain.close();
			writerTest.close();
		}
	}

	public void createFileWithMeasures(String originalName) {

		try {
			PrintWriter pw = null;

			pw = new PrintWriter(new BufferedWriter(new FileWriter(originalName + ".csv")));

			FileInputStream fs = new FileInputStream(originalName);
			DataInputStream in = new DataInputStream(fs);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split(",");

				Integer srcId = Integer.valueOf(ids[0]);
				Integer desId = Integer.valueOf(ids[1]);
				String pageRank = ids[2];
				String cls = ids[3];

				Measures m = _graph.calculateMeasures(srcId, desId);

				pw.write(pageRank + "," + m.toString() + "," + cls + "\n");
			}

			in.close();
			pw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}