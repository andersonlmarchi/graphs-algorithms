package BestFirstSearch;

import java.util.Comparator;

public class Vertex implements Comparator<Vertex> {
	private int heuristicvalue;
	private int node;

	public Vertex(int node, int heuristicvalue) {
		this.heuristicvalue = heuristicvalue;
		this.node = node;
	}

	public Vertex() { } 

	public int getHeuristicvalue() {
		return heuristicvalue;
	}

	public void setHeuristicvalue(int heuristicvalue) {
		this.heuristicvalue = heuristicvalue;
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	@Override 
	public int compare(Vertex vertex1, Vertex vertex2) {
		if (vertex1.heuristicvalue < vertex2.heuristicvalue)
			return -1;
		if (vertex1.heuristicvalue > vertex2.heuristicvalue)
			return 1;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex) {
			Vertex node = (Vertex) obj;
			if (this.node == node.node) {
				return true;
			}
		}
		return false;
	}

}
