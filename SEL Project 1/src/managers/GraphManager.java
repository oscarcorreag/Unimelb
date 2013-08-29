package managers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class GraphManager {

	public DirectedGraph<Integer, DefaultEdge> loadAdjacencyList() {

		DirectedGraph<Integer, DefaultEdge> dg = new SimpleDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		try {
			FileInputStream fstream = new FileInputStream("train_final.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int cont = 0;
			while ((strLine = br.readLine()) != null) {
				cont++;
				String[] ids = strLine.split("\t");
				Integer srcId_i = Integer.valueOf(ids[0]);
				//if (!dg.containsVertex(srcId_i))
					dg.addVertex(srcId_i);
				for (int i = 1; i < ids.length; i++) {
					Integer desId_i = Integer.valueOf(ids[i]);
					//if (!dg.containsVertex(desId_i))
						dg.addVertex(desId_i);
					dg.addEdge(srcId_i, desId_i);
				}
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return dg;
	}
}
