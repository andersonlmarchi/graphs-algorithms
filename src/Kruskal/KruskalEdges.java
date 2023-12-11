package Kruskal;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Vector;

public class KruskalEdges {

	Vector<HashSet<String>> vertexGroups = new Vector<HashSet<String>>();
	TreeSet<Edge> kruskalEdges = new TreeSet<Edge>();

	public TreeSet<Edge> getEdges() {
		return kruskalEdges;
	}

	HashSet<String> getVertexGroup(String vertex) {
		for (HashSet<String> vertexGroup : vertexGroups) {
			if (vertexGroup.contains(vertex)) {
				return vertexGroup;
			}
		}
		return null;
	}

	public void insertEdge(Edge edge) {
		String vertexA = edge.getVertexA();
		String vertexB = edge.getVertexB();

		HashSet<String> vertexGroupA = getVertexGroup(vertexA);
		HashSet<String> vertexGroupB = getVertexGroup(vertexB);

		if (vertexGroupA == null) {
			kruskalEdges.add(edge);
			if (vertexGroupB == null) {
				HashSet<String> htNewVertexGroup = new HashSet<String>();
				htNewVertexGroup.add(vertexA);
				htNewVertexGroup.add(vertexB);
				vertexGroups.add(htNewVertexGroup);
			}
			else {
				vertexGroupB.add(vertexA);           
			}
		}
		else {
			if (vertexGroupB == null) {
				vertexGroupA.add(vertexB);
				kruskalEdges.add(edge);
			}
			else if (vertexGroupA != vertexGroupB) {
				vertexGroupA.addAll(vertexGroupB);
				vertexGroups.remove(vertexGroupB);
				kruskalEdges.add(edge);
			}
		}
	}
}
