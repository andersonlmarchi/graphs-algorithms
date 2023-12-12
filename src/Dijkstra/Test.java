package Dijkstra;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

    private List<Vertex> nodes;
    private List<Edge> edges;

    public Test() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();

        this.nodes.add(new Vertex("City_1", "Viana do Castelo"));
        this.nodes.add(new Vertex("City_2", "Braga"));
        this.nodes.add(new Vertex("City_3", "Porto"));
        this.nodes.add(new Vertex("City_4", "Vila Real"));
        this.nodes.add(new Vertex("City_5", "Bragança"));
        this.nodes.add(new Vertex("City_6", "Viseu"));
        this.nodes.add(new Vertex("City_7", "Aveiro"));
        this.nodes.add(new Vertex("City_8", "Guarda"));
        this.nodes.add(new Vertex("City_9", "Coimbra"));
        this.nodes.add(new Vertex("City_10", "Castelo Branco"));
        this.nodes.add(new Vertex("City_11", "Leiria"));
        this.nodes.add(new Vertex("City_12", "Lisboa"));
        this.nodes.add(new Vertex("City_13", "Santarém"));
        this.nodes.add(new Vertex("City_14", "Portalegre"));
        this.nodes.add(new Vertex("City_15", "Évora"));
        this.nodes.add(new Vertex("City_16", "Setúbal"));
        this.nodes.add(new Vertex("City_17", "Beja"));
        this.nodes.add(new Vertex("City_18", "Faro"));

        addLane("Viana do Castelo to Braga", 0, 1, 50);
        addLane("Braga to Viana do Castelo", 1, 0, 50);

        addLane("Viana do Castelo to Porto", 0, 2, 80);
        addLane("Porto to Viana do Castelo", 2, 0, 80);

        addLane("Braga to Porto", 1, 2, 50);
        addLane("Porto to Braga", 2, 1, 50);

        addLane("Braga to Vila Real", 1, 3, 100);
        addLane("Vila Real to Braga", 3, 1, 100);

        addLane("Porto to Vila Real", 2, 3, 50);
        addLane("Vila Real to Porto", 3, 2, 50);

        addLane("Vila Real to Bragança", 3, 4, 140);
        addLane("Bragança to Vila Real", 4, 3, 140);

        addLane("Vila Real to Viseu", 3, 5, 110);
        addLane("Viseu to Vila Real", 5, 3, 110);

        addLane("Vila Real to Guarda", 3, 7, 150);
        addLane("Guarda to Vila Real", 7, 3, 150);

        addLane("Bragança to Guarda", 4, 7, 200);
        addLane("Guarda to Bragança", 7, 4, 200);

        addLane("Porto to Aveiro", 2, 6, 70);
        addLane("Aveiro to Porto", 6, 2, 70);
        
        addLane("Viseu to Aveiro", 5, 6, 100);
        addLane("Aveiro to Viseu", 6, 5, 100);

        addLane("Viseu to Guarda", 5, 7, 80);
        addLane("Guarda to Viseu", 7, 5, 80);

        addLane("Aveiro to Coimbra", 6, 8, 80);
        addLane("Coimbra to Aveiro", 8, 6, 80);

        addLane("Coimbra to Leiria", 8, 10, 70);
        addLane("Leiria to Coimbra", 10, 8, 70);

        addLane("Viseu to Coimbra", 5, 8, 80);
        addLane("Coimbra to Viseu", 8, 5, 80);

        addLane("Guarda to Castelo Branco", 7, 9, 100);
        addLane("Castelo Branco to Guarda", 9, 7, 100);

        addLane("Coimbra to Castelo Branco", 8, 9, 160);
        addLane("Castelo Branco to Coimbra", 9, 8, 160);

        addLane("Guarda to Coimbra", 7, 8, 160);
        addLane("Coimbra to Guarda", 8, 7, 160);

        addLane("Leiria to Lisboa", 10, 11, 130);
        addLane("Lisboa to Leiria", 11, 10, 130);

        addLane("Castelo Branco to Santarém", 9, 11, 200);
        addLane("Santarém to Castelo Branco", 11, 9, 200);

        addLane("Castelo Branco to Portalegre", 9, 12, 80);
        addLane("Portalegre to Castelo Branco", 12, 9, 80);

        addLane("Santarém to Lisboa", 12, 11, 70);
        addLane("Lisboa to Santarém", 11, 12, 70);

        addLane("Santarém to Portalegre", 12, 13, 150);
        addLane("Portalegre to Santarém", 13, 12, 150);

        addLane("Santarém to Évora", 13, 14, 120);
        addLane("Évora to Santarém", 14, 13, 120);

        addLane("Portalegre to Évora", 13, 14, 100);
        addLane("Évora to Portalegre", 14, 13, 100);

        addLane("Lisboa to Setúbal", 11, 15, 50);
        addLane("Setúbal to Lisboa", 15, 11, 50);

        addLane("Évora to Lisboa", 14, 11, 150);
        addLane("Lisboa to Évora", 11, 14, 150);

        addLane("Évora to Beja", 14, 16, 80);
        addLane("Beja to Évora", 16, 14, 80);

        addLane("Setúbal to Beja", 15, 16, 135);
        addLane("Beja to Setúbal", 16, 15, 135);
        
        addLane("Beja to Faro", 16, 17, 170);
        addLane("Faro to Beja", 17, 16, 170);

        addLane("Setúbal to Faro", 15, 17, 260);
        addLane("Faro to Setúbal", 17, 15, 260);
    }

    public static void main(String[] args) {
        Test test = new Test();

        // d) Test finding the shortest path
        test.findShortestPath();
        
        test.graphTest();
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, this.nodes.get(sourceLocNo), this.nodes.get(destLocNo), duration);
        this.edges.add(lane);
    }

    private void graphTest() {
        Dijkstra dijkstra = new Dijkstra(new Graph(this.nodes, this.edges));

        // b) Test checking if the graph is connected
        boolean isConnected = dijkstra.isGraphConnected();
        System.out.println("\nIs the graph connected? " + isConnected);

        // c) Test checking if the graph is complete
        boolean isComplete = dijkstra.isGraphComplete();
        System.out.println("Is the graph complete? " + isComplete);

        // e) Test checking if the graph is Eulerian
        String eulerianType = dijkstra.getEulerianType();
        System.out.println("\nGraph Eulerian Type: " + eulerianType);

        // f) Test checking if the graph is Hamiltonian
        String hamiltonianType = dijkstra.getHamiltonianType();
        System.out.println("\nGraph Hamiltonian Type: " + hamiltonianType);

        // a) Test removing a vertex
        Vertex vertexToRemove = this.nodes.get(8);
        dijkstra.removeVertex(vertexToRemove);
        System.out.println("\nGraph after removing Guarda:");
        System.out.println("Vertices: " + this.nodes);
        System.out.println("Edges: " + this.edges);
    }

    private void findShortestPath() {
        Dijkstra dijkstra = new Dijkstra(new Graph(this.nodes, this.edges));
        Vertex start = this.nodes.get(1);
        Vertex end = this.nodes.get(11);
        double distance = 0;

        LinkedList<Vertex> shortestPath = dijkstra.findShortestPath(start, end);

        System.out.println("\nShortest Path from " + start.getName() + " to " + end.getName() + ":");
        if (shortestPath != null && !shortestPath.isEmpty()) {
            for (Vertex vertex : shortestPath) {
                distance = dijkstra.getShortestDistance(vertex);
                System.out.print(vertex + " (" + dijkstra.getShortestDistance(vertex) + ") -> ");
            }
            System.out.println("Total distance: " + distance);
        } else {
            System.out.println("No path found from " + start.getName() + " to " + end.getName() + "!");
        }
    }

}
