package Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    private final List<Vertex> nodes;

    private final List<Edge> edges;

    private Set<Vertex> settledNodes;

    private Set<Vertex> unSettledNodes;

    private Map<Vertex, Vertex> predecessors;

    private Map<Vertex, Integer> distance;

    public Dijkstra(Graph graph) {
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
        initializeSets();
    }

    private void initializeSets() {
        this.settledNodes = new HashSet<>();
        this.unSettledNodes = new HashSet<>();
        this.predecessors = new HashMap<>();
        this.distance = new HashMap<>();
    }

    public void execute(Vertex source) {
        initializeSets();
        this.distance.put(source, Integer.valueOf(0));
        this.unSettledNodes.add(source);

        while (this.unSettledNodes.size() > 0) {
            Vertex node = getMinimum(this.unSettledNodes);
            this.settledNodes.add(node);
            this.unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + 
            getDistance(node, target)) {
            this.distance.put(target, 
                Integer.valueOf(getShortestDistance(node) + getDistance(node, target)));
            this.predecessors.put(target, node);
            this.unSettledNodes.add(target);
            } 
        } 
    }

    public LinkedList<Vertex> findShortestPath(Vertex start, Vertex end) {
        execute(start);
        return getPath(end);
    }    

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : this.edges) {
            if (edge.getSource().equals(node) && 
            edge.getDestination().equals(target))
            return edge.getWeight(); 
        } 
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : this.edges) {
            if (edge.getSource().equals(node) && 
            !isSettled(edge.getDestination()))
            neighbors.add(edge.getDestination()); 
        } 
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
            minimum = vertex;
            continue;
            } 
            if (getShortestDistance(vertex) < getShortestDistance(minimum))
            minimum = vertex; 
        } 
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return this.settledNodes.contains(vertex);
    }

    public int getShortestDistance(Vertex destination) {
        Integer d = this.distance.get(destination);
        if (d == null)
            return Integer.MAX_VALUE; 
        return d.intValue();
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        if (this.predecessors.get(step) == null)
            return null; 
        path.add(step);
        while (this.predecessors.get(step) != null) {
            step = this.predecessors.get(step);
            path.add(step);
        } 
        Collections.reverse(path);
        return path;
    } 

    public void removeVertex(Vertex vertexToRemove) {
        if (vertexToRemove == null || !nodes.contains(vertexToRemove)) {
            System.out.println("Vertex not found in the graph.");
            return;
        }
    
        nodes.remove(vertexToRemove);
    
        edges.removeIf(edge -> edge.getSource().equals(vertexToRemove) || edge.getDestination().equals(vertexToRemove));
    
        settledNodes.clear();
        unSettledNodes.clear();
    
        predecessors.remove(vertexToRemove);
        distance.remove(vertexToRemove);
    }
    
    public boolean isGraphConnected() {
        if (nodes.isEmpty()) {
            return false;
        }
    
        Vertex source = nodes.get(0);
        execute(source);
    
        return settledNodes.size() == nodes.size();
    }

    public boolean isGraphComplete() {
        int totalVertices = nodes.size();
        int totalEdges = edges.size();

        return totalEdges == (totalVertices * (totalVertices - 1)) / 2;
    }

    public String getEulerianType() {
        int oddDegreeCount = 0;
    
        for (Vertex node : nodes) {
            int degree = getNeighbors(node).size();
            if (degree % 2 != 0) {
                oddDegreeCount++;
            }
        }
    
        if (oddDegreeCount == 0) {
            return "Euleriano";
        } else if (oddDegreeCount == 2) {
            return "Semieuleriano";
        } else {
            return "Não Euleriano";
        }
    }

    public String getHamiltonianType() {
        int totalVertices = nodes.size();
    
        if (totalVertices < 3) {
            return "Não Hamiltoniano";
        }
    
        for (Vertex node : nodes) {
            List<Vertex> neighbors = getNeighbors(node);
    
            if (neighbors.size() == totalVertices - 1) {
                Set<Vertex> visited = new HashSet<>();
                visited.add(node);
    
                if (isHamiltonianCycle(node, node, neighbors, visited)) {
                    return "Hamiltoniano";
                }
            }
        }
    
        return "Não Hamiltoniano";
    }
    
    private boolean isHamiltonianCycle(Vertex start, Vertex current, List<Vertex> remaining, Set<Vertex> visited) {
        if (remaining.isEmpty()) {
            return current.equals(start);
        }
    
        for (Vertex next : remaining) {
            if (!visited.contains(next)) {
                visited.add(next);
                if (isHamiltonianCycle(start, next, getNeighbors(next), visited)) {
                    return true;
                }
                visited.remove(next);
            }
        }
    
        return false;
    }

}
