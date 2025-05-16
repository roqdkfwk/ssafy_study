import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.cost, e.cost);
        }
    }

    static int N, M, K;
    static List<Edge>[] edges;
    static int[] goals;
    static long[] distance;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시
        M = Integer.parseInt(st.nextToken());   // 도로
        K = Integer.parseInt(st.nextToken());   // 면접장

        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            // 면접장에서 시작하기 위해서 반대로 저장
            edges[V].add(new Edge(U, C));
        }

        // 면접장
        goals = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            goals[i] = Integer.parseInt(st.nextToken());
        }

        distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
    }

    private static void solution() {
        dijkstra();

        long maxCost = 0;
        int city = 0;
        for (int i = 1; i <= N; i++) {
            if (maxCost >= distance[i]) continue;
            city = i;
            maxCost = distance[i];
        }
        answer.append(city).append("\n").append(maxCost);
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int goal : goals) {
            distance[goal] = 0;
            pq.offer(new Edge(goal, 0));
        }

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.cost) continue;

            for (Edge next : edges[curr.to]) {
                if (distance[next.to] <= curr.cost + next.cost) continue;
                distance[next.to] = curr.cost + next.cost;
                pq.offer(new Edge(next.to, distance[next.to]));
            }
        }
    }

    private static void printResult() {
        System.out.println(answer.toString());
    }
}