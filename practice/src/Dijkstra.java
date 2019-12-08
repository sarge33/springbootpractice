import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    // edges: [src, dst, weight]
    static int minDistance(int[][] edges, int n, int src, int dst) {
        int[] distance = new int[n];
        int[] parent = new int[n];
        distance[src] = 0;
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        Map<Integer, Map<Integer, Integer>> graph = buildGrahp(edges);
        System.out.println(graph);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.add(new int[]{src, -1, 0}); //int[]: {curr, prev, weight}
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int prev = top[1];
            int weight = top[2];
            if(weight > distance[node]) continue;
            if(node == dst && weight > distance[dst]) break;
            parent[node] = prev;
            distance[node] = weight;
            Map<Integer, Integer> neighbors = graph.getOrDefault(node, new HashMap<Integer, Integer>());
            for(int neighbor: neighbors.keySet()) {
                int edgeWeight = neighbors.get(neighbor);
                int toNeighborWeight = weight + edgeWeight;
                if(toNeighborWeight < distance[neighbor]) {
                    pq.offer(new int[]{neighbor, node, toNeighborWeight});
                }
            }
        }
        printPath(parent, dst);
        return distance[dst];

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
        int n = 6;
        int[][] edges = new int[][]{
            new int[]{0, 1, 10},
            new int[]{0, 2, 15},
            new int[]{1, 3, 12},
            new int[]{1, 5, 15},
            new int[]{3, 4, 2},
            new int[]{3, 5, 1},
            new int[]{5, 4, 5}
        };
        int src = 0;
        int dst = 4;

        int dis = minDistance(edges, 6, src, dst);
        System.out.println(src +" -> " + dst + " : " + dis);
    }
}
