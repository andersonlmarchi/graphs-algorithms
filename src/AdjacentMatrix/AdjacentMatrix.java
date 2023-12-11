package AdjacentMatrix;

public class AdjacentMatrix {

    private int[][] G;

    private int vertices;

    private boolean pondered;

    private boolean directed;

    public AdjacentMatrix(int vertices, boolean pondered, boolean directed) {
        this.vertices = vertices;
        this.pondered = pondered;
        this.directed = directed;
        this.G = new int[vertices][vertices];
    }

    public void addEdge(int vertice1, int vertice2) {
        if (!this.pondered) {
            this.G[vertice1][vertice2] = 1;
            if (!this.directed) {
                this.G[vertice2][vertice1] = 1;
            }
        } else {
            System.out.println("Graph is pondered. Need to add an weight to edge.");
        }
    }

    public void addEdge(int vertice1, int vertice2, int weight) {
        if (this.pondered) {
            this.G[vertice1][vertice2] = weight;
            if (!this.directed) {
                this.G[vertice2][vertice1] = weight;
            }
        } else {
            System.out.println("Graph is not pondered. Not need to add an weight to edge.");
        }
    }

    public void removeEdge(int vertice1, int vertice2) {
        this.G[vertice1][vertice2] = 0;
        if (!this.directed) {
            this.G[vertice2][vertice1] = 0;
        }
    }

    public boolean isAdjacent(int vertice1, int vertice2) {
        return this.G[vertice1][vertice2] != 0;
    }

    public void show() {
        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                System.out.print(this.G[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacentMatrix graph = new AdjacentMatrix(5, false, false);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        graph.show();

        System.out.println();

        AdjacentMatrix graph2 = new AdjacentMatrix(5, true, false);

        graph2.addEdge(0, 1, 6);
        graph2.addEdge(0, 2, 2);
        graph2.addEdge(1, 3, 2);
        graph2.addEdge(2, 4, 5);
        graph2.addEdge(3, 4, 1);

        graph2.show();

        System.out.println();

        AdjacentMatrix graph3 = new AdjacentMatrix(5, true, true);

        graph3.addEdge(0, 1, 6);
        graph3.addEdge(0, 2, 2);
        graph3.addEdge(1, 3, 4);
        graph3.addEdge(2, 4, 5);
        graph3.addEdge(3, 4, 1);

        graph3.show();

        System.out.println("Validate if is adjacent:");

        System.out.println("is adjacent in graph the vertices 4:3? " + (graph3.isAdjacent(4, 3) ? "Yes" : "No"));
        System.out.println("is adjacent in graph the vertices 2:4? " + (graph3.isAdjacent(2, 4) ? "Yes" : "No"));

        System.out.println("is adjacent in graph2 the vertices 1:0? " + (graph3.isAdjacent(1, 0) ? "Yes" : "No"));
        System.out.println("is adjacent in graph2 the vertices 2:1? " + (graph3.isAdjacent(2, 1) ? "Yes" : "No"));

        System.out.println("is adjacent in graph3 the vertices 0:1? " + (graph3.isAdjacent(0, 1) ? "Yes" : "No"));
        System.out.println("is adjacent in graph3 the vertices 0:4? " + (graph3.isAdjacent(0, 4) ? "Yes" : "No"));

    }

}