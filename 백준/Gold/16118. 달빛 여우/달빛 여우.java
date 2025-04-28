import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        int distance;
        int state;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
            this.state = -1;
        }

        public Edge(int to, int distance, int state) {
            this.to = to;
            this.distance = distance;
            this.state = state;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.distance, e.distance);
        }
    }

    static int N, M;
    static List<Edge>[] edges;
    static int[] foxDistance, wolfDistance;
    static final int INF = Integer.MAX_VALUE;
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

        edges = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken()) * 2;

            edges[A].add(new Edge(B, L));
            edges[B].add(new Edge(A, L));
        }
    }

    private static void solution() {
        foxDistance = foxDijkstra(1);
        wolfDistance = wolfDijkstra(1);

        for (int i = 1; i <= N; i++) {
            if (foxDistance[i] < wolfDistance[i]) answer++;
        }
    }

    private static int[] foxDijkstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.distance) continue;

            for (Edge e : edges[curr.to]) {
                int newDist = curr.distance + e.distance;
                if (distance[e.to] > newDist) {
                    distance[e.to] = newDist;
                    pq.add(new Edge(e.to, newDist));
                }
            }
        }

        return distance;
    }

    private static int[] wolfDijkstra(int start) {
        int[][] distance = new int[2][N + 1];
        Arrays.fill(distance[0], INF);
        Arrays.fill(distance[1], INF);
        distance[0][start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.state][curr.to] < curr.distance) continue;

            for (Edge next : edges[curr.to]) {
                int nextDist;
                int nextState = 1 - curr.state;

                // 빠르게 이동
                if (curr.state == 0) {
                    nextDist = curr.distance + (next.distance / 2);
                }
                // 느리게 이동
                else {
                    nextDist = curr.distance + (next.distance * 2);
                }

                if (distance[nextState][next.to] > nextDist) {
                    distance[nextState][next.to] = nextDist;
                    pq.add(new Edge(next.to, nextDist, nextState));
                }
            }
        }

        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = Math.min(distance[0][i], distance[1][i]);
        }

        return result;
    }

    private static void printResult() {
        System.out.println(answer);
    }
}