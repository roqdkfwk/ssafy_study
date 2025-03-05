import java.util.*;
import java.io.*;
public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long length;

        public Edge (int to, long length) {
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.length, e.length);
        }
    }

    static int N, M;
    static List<Edge>[] edges;
    static Set<Integer> restricted;
    static long answer = -1;

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        restricted = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) {
                restricted.add(i);
            }
        }
        Integer.parseInt(st.nextToken());

        edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            if (restricted.contains(A) || restricted.contains(B)) continue;

            edges[A].add(new Edge(B, L));
            edges[B].add(new Edge(A, L));
        }
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        long[] distance = new long[N];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[0] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.to == N - 1) {
                answer = curr.length;
                return;
            }

            if (curr.length > distance[curr.to]) continue;

            for (Edge e : edges[curr.to]) {
                if (distance[e.to] <= curr.length + e.length) continue;

                distance[e.to] = curr.length + e.length;
                pq.add(new Edge(e.to, curr.length + e.length));
            }
        }
    }

    private static void printResult() {
        System.out.println(answer);
    }
}