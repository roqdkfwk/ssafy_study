import java.util.*;
import java.io.*;
public class Main {

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }

    static int N, M;
    static int[][] grid;
    static List<Edge>[] edges;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges[A].add(new Edge(B, C));
            edges[B].add(new Edge(A, C));
        }
    }

    private static void solution() {
        answer = dijkstra(1);
    }

    private static int dijkstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.cost) continue;

            for (Edge e : edges[curr.to]) {
                if (distance[e.to] <= curr.cost + e.cost) continue;

                distance[e.to] = curr.cost + e.cost;
                pq.add(new Edge(e.to, distance[e.to]));
            }
        }

        return distance[N];
    }

    private static void printResult() {
        System.out.println(answer);
    }
}