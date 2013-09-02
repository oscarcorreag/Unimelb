package managers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import dto.Edge;
import dto.Graph;
import dto.Measures;

public class GraphManager {

	public Graph _graph;

	final int MAX = 4;
	final int NUM_THREADS = 8;

	public GraphManager() {
		_graph = new Graph();
	}

	/**
	 * Read the test file given in the contest.
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
	 * 
	 * @param test_vertices
	 * @param in_file
	 */
	public void readTrainGraphFromFile(HashSet<Integer> test_vertices, String in_file) {

		try {
			// Read the input file which
			FileInputStream fstream = new FileInputStream(in_file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

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

			Map<Integer, HashSet<Integer>> vertexAndFollowees = _graph.getVertexAndFollowees();

			Random random = new Random();
			List<Integer> keys = new ArrayList<Integer>(vertexAndFollowees.keySet());
			Iterator<Entry<Integer, HashSet<Integer>>> it = vertexAndFollowees.entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry<Integer, HashSet<Integer>> vAndF = (Map.Entry<Integer, HashSet<Integer>>) it.next();

				Integer follower = vAndF.getKey();
				HashSet<Integer> followees = vAndF.getValue();

				HashSet<Integer> notFollowees = new HashSet<Integer>();
				for (int i = 0; i < MAX; i++) {
					Integer randomKey = keys.get(random.nextInt(keys.size()));

					if (follower != randomKey && !followees.contains(randomKey)) {
						notFollowees.add(randomKey);
					}
				}
				_graph.addVertexAndNotFollowees(follower, notFollowees);

				for (Integer followee : followees)
					if (vertexAndFollowees.containsKey(followee) || test_vertices.contains(followee))
						_graph.addVertexAndFollower(followee, follower);
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param vertices_set
	 * @param out_file
	 */
	public void writeTrainFile(HashSet<Integer> vertices_set, String out_file) {

		File trainFile = new File(out_file);

		if (!trainFile.exists()) {
			try {
				trainFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		PrintWriter writer = null;

		try {

			writer = new PrintWriter(trainFile, "UTF-8");

			Set<Integer> dest_ids;

			Map<Integer, HashSet<Integer>> vertexAndNotFollowees = _graph.getVertexAndNotFollowees();

			for (Integer src_id : vertices_set) {
				// long t0 = System.currentTimeMillis();

				HashMap<Integer, Double> init_probs = new HashMap<Integer, Double>();
				init_probs.put(src_id, 1.0);

				Map<Integer, Double> pr_probs = _graph.calcPageRank(src_id, init_probs, 2);
				// System.out.println(System.currentTimeMillis() - t0);

				dest_ids = _graph.getFollowees(src_id);

				int i = 0;

				if (dest_ids != null) {

					for (Integer dest_id : dest_ids) {

						if (i > MAX)
							break;

						if (_graph.getFollowees(dest_id) == null)
							continue;

						Measures m = _graph.calculateMeasures(src_id, dest_id, pr_probs);
						// Measures m = _graph.calculateMeasures(src_id,
						// dest_id);
						writer.write(m.toString() + ",1\n");
						i++;
					}
				}

				Set<Integer> dest_ids_neg = vertexAndNotFollowees.get(src_id);

				if (dest_ids_neg != null) {
					for (Integer dest_id_neg : dest_ids_neg) {
						Measures m = _graph.calculateMeasures(src_id, dest_id_neg, pr_probs); // pr_map_src
						writer.write(m.toString() + ",-1\n");
					}
				}

			}

			// Iterator<Entry<Integer, HashSet<Integer>>> it =
			// vertexAndNotFollowees.entrySet().iterator();
			//
			// while (it.hasNext()) {
			//
			// Map.Entry<Integer, HashSet<Integer>> vAndF = (Map.Entry<Integer,
			// HashSet<Integer>>) it.next();
			//
			// Integer notFollower = vAndF.getKey();
			// HashSet<Integer> notFollowees = vAndF.getValue();
			//
			// for (Integer notFollowee : notFollowees){
			// Measures m = _graph.calculateMeasures(notFollower, notFollowee);
			// writer.write(m.toString() + ",-1\n");
			// }
			// }

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public void writeTestFile(String in_file, String out_file) {

		File testFile = new File(out_file);
		if (!testFile.exists()) {
			try {
				testFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(testFile, "UTF-8");

			FileInputStream fstream = new FileInputStream(in_file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			Measures m;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");

				Integer srcId_i = Integer.valueOf(ids[0]);
				Integer destId_i = Integer.valueOf(ids[1]);

				HashMap<Integer, Double> init_probs = new HashMap<Integer, Double>();
				init_probs.put(srcId_i, 1.0);

				Map<Integer, Double> pr_probs = _graph.calcPageRank(srcId_i, init_probs, 2);

				// m = _graph.calculateMeasures(srcId_i, destId_i);
				m = _graph.calculateMeasures(srcId_i, destId_i, pr_probs);
				writer.write(m.toString() + ",?\n");
			}
			in.close();
			writer.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public ArrayList<Edge> getTestEdges(String test_file) {

		ArrayList<Edge> edges = new ArrayList<Edge>();

		try {
			FileInputStream fstream = new FileInputStream(test_file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");

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

	public void createThreads(ArrayList<Edge> edges, String trainFileName, String testFileName) {

		ArrayList<Worker> workers = new ArrayList<Worker>();

		int factor = edges.size() / NUM_THREADS;
		int i;
		for (i = 0; i < NUM_THREADS - 1; i++) {
			ArrayList<Edge> subListEdges = (ArrayList<Edge>) edges.subList(i * factor, (i + 1) * factor);
			workers.add(new Worker(_graph, subListEdges, trainFileName + "_" + i + ".txt", testFileName + "_" + i + ".txt"));
		}
		ArrayList<Edge> subListEdges = (ArrayList<Edge>) edges.subList(i * factor, edges.size());
		workers.add(new Worker(_graph, subListEdges, trainFileName + "_" + i + ".txt", testFileName + "_" + i + ".txt"));

		for (Worker w : workers)
			w.run();
		for (Worker w : workers)
			try {
				w.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}