import java.io.*;
import java.util.*;

public class Main {
	
    static class Edge implements Comparable<Edge> {
        int num, dist;
        
        Edge(int node, int dist) {
            this.num = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static int N, M, K;
    static List<Edge>[] edges;
    static PriorityQueue<Integer>[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        edges = new ArrayList[N + 1];
        distances = new PriorityQueue[N + 1];        
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
            distances[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            edges[a].add(new Edge(b, c));
        }
        
        dijkstra();
        
        for (int i = 1; i <= N; i++) {
            if (distances[i].size() == K) {
                sb.append(distances[i].peek() + "\n");
            } else {
                sb.append(-1).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        distances[1].add(0);
        
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            
            for (Edge next : edges[curr.num]) {	// curr.dist는 1번째 Edge에서 curr번째 Edge까지의 '누적거리'
            	if (distances[next.num].size() < K) {
            		distances[next.num].add(curr.dist + next.dist);
            		pq.add(new Edge(next.num, curr.dist + next.dist));
            	} else if (distances[next.num].peek() > curr.dist + next.dist) {
            		distances[next.num].poll();
                    distances[next.num].add(curr.dist + next.dist);
                    pq.add(new Edge(next.num, curr.dist + next.dist));
                }
            }
        }
    }	// dijkstra
}