package dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph {

	private static final double PR_alpha = 0.5;

	Map<Integer, HashSet<Integer>> _vertexAndFollowees;
	Map<Integer, HashSet<Integer>> _vertexAndFollowers;

	Map<Integer, HashSet<Integer>> _vertexAndNotFollowees;

	public Graph() {

		_vertexAndFollowees = new HashMap<Integer, HashSet<Integer>>();
		_vertexAndFollowers = new HashMap<Integer, HashSet<Integer>>();

		_vertexAndNotFollowees = new HashMap<Integer, HashSet<Integer>>();
	}

	public void addVertexAndFollowees(Integer v, HashSet<Integer> n) {
		_vertexAndFollowees.put(v, n);
	}

	public void addVertexAndNotFollowees(Integer v, HashSet<Integer> n) {
		_vertexAndNotFollowees.put(v, n);
	}

	public void addVertexAndFollower(Integer dest, Integer src) {
		if (_vertexAndFollowers.containsKey(dest)) {
			_vertexAndFollowers.get(dest).add(src);
		} else {
			HashSet<Integer> followers = new HashSet<Integer>();
			followers.add(src);
			_vertexAndFollowers.put(dest, followers);
		}
	}

	public int numOfFollowers(Integer v) {
		if (_vertexAndFollowers.containsKey(v))
			return _vertexAndFollowers.get(v).size();
		else
			return 0;
	}

	public int numOfFollowees(Integer v) {
		if (_vertexAndFollowees.containsKey(v))
			return _vertexAndFollowees.get(v).size();
		else
			return 0;
	}

	/**
	 * Simulates running a personalized PageRank for one iteration.
	 * 
	 * Parameters: start_node - the start node to calculate the personalized
	 * PageRank around map_probs - a map from nodes to the probability of being
	 * at that node at the start of the current iteration numIterations - the
	 * number of iterations remaining alpha - with probability alpha, we follow
	 * a neighbor; with probability 1 - alpha, we teleport back to the start
	 * node
	 * 
	 * @return A map of node -> probability of landing at that node after the
	 *         specified number of iterations.
	 */
	public HashMap<Integer, Double> calcPageRank(Integer start_node, HashMap<Integer, Double> init_probs, int numIterations) {
		if (numIterations <= 0) {
			return init_probs;
		} else {
			HashMap<Integer, Double> updated_probs = new HashMap<Integer, Double>();
			updated_probs.put(start_node, 1 - PR_alpha);

			Double probToPropagate;
			Double p;
			for (Map.Entry<Integer, Double> entry : init_probs.entrySet()) {
				Integer node = entry.getKey();
				Double prob = entry.getValue();
				HashSet<Integer> neighbours = new HashSet<Integer>();
				boolean foll1 = _vertexAndFollowees.containsKey(node);
				boolean foll2 = _vertexAndFollowers.containsKey(node);
				if (!(foll1 || foll2))
					continue;
				if (foll1)
					neighbours.addAll(_vertexAndFollowees.get(node));
				if (foll2)
					neighbours.addAll(_vertexAndFollowers.get(node));
				Double log_sz = Math.log(neighbours.size());
				probToPropagate = (PR_alpha * prob) / log_sz;
				for (Integer neighbour : neighbours) {
					if (init_probs.containsKey(neighbour))
						p = init_probs.get(neighbour);
					else
						p = 0.0;

					updated_probs.put(neighbour, p + probToPropagate);
				}
			}
			return calcPageRank(start_node, updated_probs, numIterations - 1);
		}

	}

	public Measures calculateMeasures(Integer v1, Integer v2, HashMap<Integer, Double> pr_probs) {

		Measures m = new Measures();

		HashSet<Integer> followees_v1 = _vertexAndFollowees.get(v1);
		HashSet<Integer> followees_v2 = _vertexAndFollowees.get(v2);

		HashSet<Integer> followers_v1 = _vertexAndFollowers.get(v1);
		HashSet<Integer> followers_v2 = _vertexAndFollowers.get(v2);

		if (followees_v1 != null)
			m.setNumFolloweesSrc(followees_v1.size());

		if (followees_v2 != null) {
			m.setMutual(followees_v2.contains(v1) ? 1 : 0);
			m.setNumFolloweesDes(followees_v2.size());
		}

		if (followers_v1 != null)
			m.setNumFollowersSrc(followers_v1.size());

		if (followers_v2 != null)
			m.setNumFollowersDes(followers_v2.size());

		if (followees_v1 != null && followees_v2 != null) {

			HashSet<Integer> followees_i = new HashSet<Integer>(followees_v2);
			HashSet<Integer> followees_u = new HashSet<Integer>(followees_v2);

			followees_i.retainAll(followees_v1);
			followees_u.addAll(followees_v1);

			m.setIntersectionFollowees(followees_i.size());

			if (followees_u.size() > 0)
				m.setJaccardFollowees(m.getIntersectionFollowees() / followees_u.size());

			if (followees_v1.size() > 0 && followees_v2.size() > 0)
				m.setCosineFollowees(m.getIntersectionFollowees() / Math.sqrt(followees_v1.size() * followees_v2.size()));
		}

		if (followers_v1 != null && followers_v2 != null) {

			HashSet<Integer> followers_i = new HashSet<Integer>(followers_v2);
			HashSet<Integer> followers_u = new HashSet<Integer>(followers_v2);

			followers_i.retainAll(followers_v1);
			followers_u.addAll(followers_v1);

			m.setIntersectionFollowers(followers_i.size());

			if (followers_u.size() > 0)
				m.setJaccardFollowers(m.getIntersectionFollowers() / followers_u.size());

			if (followers_v1.size() > 0 && followers_v2.size() > 0)
				m.setCosineFollowers(m.getIntersectionFollowers() / Math.sqrt(followers_v1.size() * followers_v2.size()));
		}		

		if (pr_probs.containsKey(v2))
			m.setPageRank(pr_probs.get(v2));
		
		return m;
	}

	public HashSet<Integer> getFollowers(Integer v) {
		if (_vertexAndFollowers.containsKey(v))
			return _vertexAndFollowers.get(v);
		else
			return null;
	}

	public HashSet<Integer> getFollowees(Integer v) {
		if (_vertexAndFollowees.containsKey(v))
			return _vertexAndFollowees.get(v);
		else
			return null;
	}

	public Map<Integer, HashSet<Integer>> getVertexAndFollowees() {
		return _vertexAndFollowees;
	}

	public Map<Integer, HashSet<Integer>> getVertexAndNotFollowees() {
		return _vertexAndNotFollowees;
	}

}