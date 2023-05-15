public class PrimAlgorithm {
    public static void primMST(int[][] graph, int numVertices) {
        int[] parent = new int[numVertices]; // to store the parent of each vertex
        int[] key = new int[numVertices]; // to store the minimum weight of each vertex
        boolean[] mstSet = new boolean[numVertices]; // to mark whether a vertex has been included in the MST

        // initialize key values to infinity and mstSet to false
        for (int i = 0; i < numVertices; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // start with vertex 0
        key[0] = 0;
        parent[0] = -1; // no parent of vertex 0
        // find MST with V-1 edges
        for (int count = 0; count < numVertices - 1; count++) {
            int u = minKey(key, mstSet, numVertices); // find the vertex with minimum key value
            mstSet[u] = true; // mark it as included in the MST
            // update key values of adjacent vertices if they are not already in the MST and their weight is smaller than the current key value
            for (int v = 0; v < numVertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        // print the MST
//        System.out.println("Edge \tWeight");
//        for (int i = 1; i < numVertices; i++) {
//            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
//        }
    }
    // utility function to find the vertex with minimum key value
    private static int minKey(int[] key, boolean[] mstSet, int numVertices) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < numVertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}
