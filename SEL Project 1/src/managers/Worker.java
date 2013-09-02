package managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dto.Edge;
import dto.Graph;
import dto.Measures;

public class Worker implements Runnable {

	private static final int NUM_ITERATIONS = 2;
	Graph _graph;
	List<Edge> _edges;
	String _trainFileName;
	String _testFileName;

	public Worker(Graph _graph, List<Edge> subListEdges, String _trainFileName, String _testFileName) {
		this._graph = _graph;
		this._edges = subListEdges;
		this._trainFileName = _trainFileName;
		this._testFileName = _testFileName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		writeFiles();
	}

	private void writeFiles() {

		File trainFile = new File(_trainFileName);
		File testFile = new File(_testFileName);

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

		try {
			writerTrain = new PrintWriter(new BufferedWriter(new FileWriter(_trainFileName)));
			writerTest = new PrintWriter(new BufferedWriter(new FileWriter(_testFileName)));

			for (Edge edge : _edges) {

				Integer src_id = edge.getSourceId();
				//check if it is in the verticeAndFollowees

				Map<Integer, Double> initProbs = new HashMap<Integer, Double>();
				initProbs.put(src_id, 1.0);
				Map<Integer, Double> pageRankProbs = _graph.calcPageRank(src_id, initProbs, NUM_ITERATIONS);

				Set<Integer> posDestIds = _graph.getRandomPositiveEdges(src_id);
				for (Integer dest_id : posDestIds) {
					Measures m = _graph.calculateMeasures(src_id, dest_id, pageRankProbs);
					writerTrain.write(m.toString() + ",1\n");
				}

				Set<Integer> negDestIds = _graph.getRandomNegativeEdges(src_id);
				if (negDestIds != null) {
					for (Integer dest_id : negDestIds) {
						Measures m = _graph.calculateMeasures(src_id, dest_id, pageRankProbs);
						writerTrain.write(m.toString() + ",-1\n");
					}
				}

				Measures m = _graph.calculateMeasures(src_id, edge.getDestinationId(), pageRankProbs);
				writerTest.write(m.toString() + ",?\n");

			}
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

}
