import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 0;
        int density = 50;
        for (int i = 0; i < 5; i++) {
            n += 20;
            int[][] graph = generateRandomGraph(n, density);

            long startTime = System.nanoTime();
//            Dijkstra.dijkstra(graph, 0);
            PrimAlgorithm.primMST(graph, graph.length);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Nodes: " + n);
//            System.out.println("Dijkstra time taken: " + totalTime + " nano seconds");
            System.out.println("Prim time taken: " + totalTime + " nano seconds");

            long startTime2 = System.nanoTime();
//            FloydWarshall.floydWarshall(graph, graph.length);
            KruskalAlgorithm.kruskal(graph);
            long endTime2 = System.nanoTime();
            long totalTime2 = endTime2 - startTime2;
//            System.out.println("FloydWarshall time taken: " + totalTime2 + " nano seconds");
            System.out.println("Kruskal time taken: " + totalTime2 + " nano seconds");
            System.out.println();
        }

    }

    public static int[][] generateRandomGraph(int n, int density) {
        int[][] graph = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else if (rand.nextInt(20) < density) {
                    int weight = rand.nextInt(20) + 1;
                    graph[i][j] = weight;
                    graph[j][i] = weight;
                } else {
                    graph[i][j] = Integer.MAX_VALUE;
                    graph[j][i] = Integer.MAX_VALUE;
                }
            }
        }
        return graph;
    }
}