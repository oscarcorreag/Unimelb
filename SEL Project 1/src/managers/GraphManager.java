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
import java.util.HashSet;
import java.util.Set;

import dto.Graph;
import dto.Measures;

public class GraphManager {

	public Graph _graph;

	// final String train_1 = "train_toy.txt";
	// final String test_1 = "test_toy.txt";

	final String train_1 = "train_final.txt";
	final String test_1 = "test_final.txt";

	final String train_2 = "train_set.txt";
	final String test_2 = "test_set.txt";

	public GraphManager() {
		_graph = new Graph();
	}

	public HashSet<Integer> readTestVertices() {
		HashSet<Integer> vertices = new HashSet<Integer>();
		try {
			// FileInputStream fstream = new FileInputStream("test_final.txt");
			FileInputStream fstream = new FileInputStream(test_1);
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

	public void writeTrainFile(HashSet<Integer> vertices_set) {

		File trainFile = new File(train_2);
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
			// int i = 0;
			for (Integer src_id : vertices_set) {
				dest_ids = _graph.getFollowees(src_id);
				// i = 0;
				Measures m;
				if (dest_ids != null) {
					for (Integer dest_id : dest_ids) {
						if (_graph.getFollowees(dest_id) == null)
							break;
						m = _graph.calculateMeasures(src_id, dest_id);
						writer.write(m.toString());
					}
				}
			}
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

	public void writeTestFile() {

		File testFile = new File(test_2);
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

			FileInputStream fstream = new FileInputStream(test_1);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			Measures m;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");

				Integer srcId_i = Integer.valueOf(ids[0]);
				Integer destId_i = Integer.valueOf(ids[1]);

				m = _graph.calculateMeasures(srcId_i, destId_i);
				writer.write(m.toString());
			}
			in.close();
			writer.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void readTrainGraphFromFile(HashSet<Integer> test_vertices) {

		try {
			// FileInputStream fstream = new FileInputStream("train_final.txt");
			FileInputStream fstream = new FileInputStream(train_1);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null) {
				String[] ids = strLine.split("\t");
				Integer srcId_i = Integer.valueOf(ids[0]);
				HashSet<Integer> n = new HashSet<Integer>();

				for (int i = 1; i < ids.length; i++) {
					Integer desId_i = Integer.valueOf(ids[i]);
					n.add(desId_i);
					if (test_vertices.contains(desId_i))
						_graph.addFollowee(desId_i, srcId_i);
				}
				_graph.add(srcId_i, n);
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}