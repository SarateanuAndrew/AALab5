import java.util.Arrays;
import java.util.Comparator;

public class KruskalAlgorithm {
    public static int[][] kruskal(int[][] adjacencyMatrix) {
        int numVertices = adjacencyMatrix.length;
        int[] parent = new int[numVertices];
        int[][] result = new int[numVertices - 1][2];
        int numEdges = 0;

        // Initialize parent array with each vertex as its own parent
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
        }

        // Create a copy of the adjacency matrix to avoid modifying the original
        int[][] copy = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, copy[i], 0, numVertices);
        }

        // Sort the edges by weight
        int[] weights = new int[numVertices * numVertices];
        int[] sources = new int[numVertices * numVertices];
        int[] destinations = new int[numVertices * numVertices];
        int index = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (copy[i][j] != 0) {
                    weights[index] = copy[i][j];
                    sources[index] = i;
                    destinations[index] = j;
                    copy[i][j] = 0;
                    index++;
                }
            }
        }
        int[][] edges = new int[index][3];
        for (int i = 0; i < index; i++) {
            edges[i][0] = weights[i];
            edges[i][1] = sources[i];
            edges[i][2] = destinations[i];
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> a[0]));

        // Add edges to the result array in ascending order of weight
        for (int i = 0; i < index; i++) {
            int[] edge = edges[i];
            int sourceParent = find(parent, edge[1]);
            int destParent = find(parent, edge[2]);
            if (sourceParent != destParent) {
                result[numEdges][0] = edge[1];
                result[numEdges][1] = edge[2];
                numEdges++;
                parent[sourceParent] = destParent;
            }
            if (numEdges == numVertices - 1) {
                break;
            }
        }

        return result;
    }

    private static int find(int[] parent, int vertex) {
        while (parent[vertex] != vertex) {
            vertex = parent[vertex];
        }
        return vertex;
    }

}
