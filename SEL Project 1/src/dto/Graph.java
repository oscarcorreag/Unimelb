package dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph {

	Map<Integer, HashSet<Integer>> _vertexAndFollowees;
	Map<Integer, HashSet<Integer>> _vertexAndFollowers;

	public Graph() {

		_vertexAndFollowees = new HashMap<Integer, HashSet<Integer>>();
		_vertexAndFollowers = new HashMap<Integer, HashSet<Integer>>();
	}

	public void addVertexAndFollowees(Integer v, HashSet<Integer> n) {
		_vertexAndFollowees.put(v, n);
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

	public Measures calculateMeasures(Integer v1, Integer v2) {

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
}