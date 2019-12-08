import java.util.*;

public class Prim {

    // edges: [src, dst, weight]
    static int minDistance(int[][] edges) {
        Map<Integer, Map<Integer, Integer>> graph = buildGrahp(edges);
        System.out.println(graph);
        Arrays.sort(edges, (a,b) -> a[2] - b[2]);
        Set<Integer> visited = new HashSet<>();
        visited.add(edges[0][0]);
        visited.add(edges[0][1]);
        int distance = edges[0][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);

        int[] nodes = new int[]{edges[0][0], edges[0][1]};
        System.out.println(edges[0][0] + "->" + edges[0][1]+ "[" + edges[0][2] +"] ");

        for(int node: nodes) {
            Map<Integer, Integer> neighbors = graph.getOrDefault(node, new HashMap<Integer, Integer>());
            for (int neighbor : neighbors.keySet()) {
                if(visited.contains(neighbor)) continue;
                int edgeWeight = neighbors.get(neighbor);
                pq.offer(new int[]{node, neighbor, edgeWeight});
            }
        }


        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int neighbor = top[1];
            int weight = top[2];
            if(visited.contains(neighbor)) continue;
            visited.add(neighbor);
            distance += weight;
            System.out.println(node + "->" + neighbor+ "[" + weight +"] ");
            Map<Integer, Integer> neighbors = graph.getOrDefault(neighbor, new HashMap<Integer, Integer>());
            for (int next : neighbors.keySet()) {
                if(visited.contains(next)) continue;
                int edgeWeight = neighbors.get(next);
                pq.offer(new int[]{neighbor, next, edgeWeight});
            }
        }

        return distance;
    }

    static void printPath(int[] parent, int dst) {
        while(dst != -1) {
            System.out.print(dst +" ");
            dst = parent[dst];
        }
        System.out.println();
    }

    static Map<Integer, Map<Integer, Integer>> buildGrahp(int[][]edges) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] edge: edges) {
            int src = edge[0];
            int dst = edge[1];
            int weight = edge[2];
            map.putIfAbsent(src, new HashMap<>());
            map.get(src).put(dst, weight);
        }
        return map;
    }

    public static void  main(String[] args) {
        // https://www.baeldung.com/java-dijkstra
        // https://www.youtube.com/watch?v=cplfcGZmX7I
        int[][] edges = new int[][]{
            new int[]{0, 1, 2},
            new int[]{1, 0, 2},
            new int[]{0, 2, 3},
            new int[]{2, 0, 3},
            new int[]{0, 3, 3},
            new int[]{3, 0, 3},
            new int[]{1, 2, 4},
            new int[]{2, 1, 4},
            new int[]{1, 4, 3},
            new int[]{4, 1, 3},
            new int[]{2, 4, 1},
            new int[]{4, 2, 1},
            new int[]{3, 2, 5},
            new int[]{2, 3, 5},
            new int[]{2, 5, 6},
            new int[]{5, 2, 6},
            new int[]{3, 5, 7},
            new int[]{5, 3, 7},
            new int[]{4, 5, 8},
            new int[]{5, 4, 8},
            new int[]{6, 5, 9},
            new int[]{5, 6, 9}
        };

        int dis = minDistance(edges);
        System.out.println("minimum spanning tree distsance: " + dis);
    }
}
