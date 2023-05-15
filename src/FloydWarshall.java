public class FloydWarshall {
    static final int INF = Integer.MAX_VALUE;

    public static void floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];
        int i, j, k;

        // Initialize distances to the graph's edge weights
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Calculate shortest distances using dynamic programming
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the calculated shortest distances
//        printDistances(dist, V);
    }

    public static void printDistances(int[][] dist, int V) {
        System.out.println("The following matrix shows the shortest " +
                "distances between every pair of vertices:");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }
}
