import java.io.*;
import java.util.*;

public class Main {
    
    static class Edge implements Comparable<Edge> {
        int to, length;
        
        public Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.length - e.length;
        }
    }   // Edge
    
    static int N, E;
    static int v1, v2;
    static List<Edge>[] edges;
    static int[] dist;
    static final int INF = 200000000;  // 최대 거리보다 큰 값으로 설정
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));  // 양방향 간선 추가
        }
        
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        
        long ans1 = (long)dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        long ans2 = (long)dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
        
        long result = Math.min(ans1, ans2);
        System.out.println(result >= INF ? -1 : result);
    }   // main

    private static int dijkstra(int start, int end) {
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int cv = current.to;
            
            if (cv == end) return dist[end];
            if (current.length > dist[cv]) continue;
            
            for (Edge next : edges[cv]) {
                if (dist[next.to] > dist[cv] + next.length) {
                    dist[next.to] = dist[cv] + next.length;
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
        
        return INF;
    }
}