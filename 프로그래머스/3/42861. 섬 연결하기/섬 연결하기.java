import java.util.*;

class Solution {
    
    class Edge implements Comparable<Edge> {
        int A, B, Cost;
        
        public Edge (int A, int B, int Cost) {
            this.A = A;
            this.B = B;
            this.Cost = Cost;
        }
        
        @Override
        public int compareTo(Edge E) {
            return this.Cost - E.Cost;
        }
    }
    
    int n;
    int[][] costs;
    PriorityQueue<Edge> edges;
    int[] parent;
    int answer;
    
    public int solution(int n, int[][] costs) {
        this.n = n;
        this.costs = costs;
        
        edges = new PriorityQueue<>();
        for (int i = 0; i < costs.length; i++) {
            int A = costs[i][0];
            int B = costs[i][1];
            int C = costs[i][2];
            
            edges.add(new Edge(A, B, C));
        }
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int count = 0;
        while (true) {
            Edge edge = edges.poll();
            
            int parA = findParent(edge.A);
            int parB = findParent(edge.B);
            
            if (parA != parB) {
                union(parA, parB);
                answer += edge.Cost;
                count++;
            }
            
            if (count == n - 1) {
                break;
            }
        }
        
        return answer;
    }
    
    private int findParent(int x) {
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        
        return parent[x];
    }
    
    private void union(int A, int B) {
        parent[findParent(B)] = findParent(A);
    }
}






















