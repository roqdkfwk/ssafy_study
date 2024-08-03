import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            int i = path[0], j = path[1], w = path[2];
            graph.get(i).add(new Node(j, w));
            graph.get(j).add(new Node(i, w));
        }
        
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }
        
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            if (current.weight > intensity[current.to]) continue;
            if (summitSet.contains(current.to)) continue;
            
            for (Node next : graph.get(current.to)) {
                int newIntensity = Math.max(intensity[current.to], next.weight);
                if (newIntensity < intensity[next.to]) {
                    intensity[next.to] = newIntensity;
                    pq.offer(new Node(next.to, newIntensity));
                }
            }
        }
        
        int minIntensity = Integer.MAX_VALUE;
        int minSummit = 0;
        
        Arrays.sort(summits);
        
        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }
        
        return new int[]{minSummit, minIntensity};
    }
    
    class Node {
        int to, weight;
        
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}