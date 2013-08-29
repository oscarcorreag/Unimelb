package managers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.xml.soap.Node;
  class Node {

	public int data; // data element
	public boolean visited=false; // flag to track the already visited node
	public List<Node> adjacentNodes = new LinkedList(); // adjacency list

	public Node(int data){
		this.data = data;
	}

	public void addAdjacentNode(final Node node){
		adjacentNodes.add(node);
		node.adjacentNodes.add(this);
	}

}

public class Graphhh {
	// Map of adjacency lists for each node
 	private List nodes = new ArrayList();

	public void breadthFirstTraversal(Node rootNode){

		Queue q = new LinkedList();
		q.add(rootNode);
		rootNode.visited=true;
		while(!q.isEmpty()){
			Node n = (Node)q.poll();
			System.out.print(n.data + " ");
			for(Node adj : n.adjacentNodes){
				if(!adj.visited){
					adj.visited=true;
					q.add(adj);
				}
			}
		}

	}

	public Graphhh() {

		 
	}

	 
	public int calcCN(Integer v1, Integer v2) {

		int cn = 0;
		if (adj.containsKey(v1) && adj.containsKey(v2)) {
			HashSet<Integer> n1 = adj.get(v1);
			HashSet<Integer> n2 = new HashSet<Integer>(adj.get(v2));

			n2.retainAll(n1);

			cn = n2.size();
		}
		return cn;
	}

	public HashSet<Integer> readTestVertices() {
		HashSet<Integer> vertices = new HashSet<Integer>();
		try {
			FileInputStream fstream = new FileInputStream("test_final.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");

				Integer srcId_i = Integer.valueOf(ids[0]);
				Integer DestId_i = Integer.valueOf(ids[1]);
				vertices.add(srcId_i);
				vertices.add(DestId_i);

			}
			in.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return vertices;

	}

	public void calculateMeasures() {

		try {
			FileInputStream fstream = new FileInputStream("test_final.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");

			writer.write("Predictions\n");

			while ((strLine = br.readLine()) != null) {

				String[] ids = strLine.split("\t");

				Integer srcId_i = Integer.valueOf(ids[0]);
				Integer DestId_i = Integer.valueOf(ids[1]);

				int cn = calcCN(srcId_i, DestId_i);
				if (cn > 0)
					writer.write("1\n");
				else
					writer.write("0\n");

			}
			in.close();
			writer.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void readTrainData() {
		try {
			FileInputStream fstream = new FileInputStream("train_final.txt");
			// FileInputStream fstream = new
			// FileInputStream("train_sample.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int cont = 0;
			while ((strLine = br.readLine()) != null) {
				cont++;
				String[] ids = strLine.split("\t");
				Integer srcId_i = Integer.valueOf(ids[0]);
				Node node = new Node(srcId_i);
				 
				for (int i = 1; i < ids.length; i++) {
					Integer desId_i = Integer.valueOf(ids[i]);
					node.addAdjacentNode(node).add(desId_i);

				}
				this.add(srcId_i, n);
			}
			in.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
