import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        int distance;

        public Edge(int t, int d) {
            this.to = t;
            this.distance = d;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.distance, e.distance);
        }
    }

    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            List<Edge>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Edge(a, s));
            }

            int[] ans = solution(N, C, graph);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }
    }

    private static int[] dijkstra(int N, int start, List<Edge>[] graph) {
        int[] time = new int[N + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.distance > time[curr.to]) continue;

            for (Edge edge : graph[curr.to]) {
                if (curr.distance + edge.distance < time[edge.to]) {
                    time[edge.to] = curr.distance + edge.distance;
                    pq.add(new Edge(edge.to, time[edge.to]));
                }
            }
        }
        return time;
    }

    private static int[] solution(int N, int com, List<Edge>[] graph) {
        int[] time = dijkstra(N, com, graph);
        int count = 0;
        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            if (time[i] != Integer.MAX_VALUE) {
                count++;
                maxTime = Math.max(maxTime, time[i]);
            }
        }
        return new int[] {count, maxTime};
    }

    private static void printResult() {
        System.out.print(sb.toString());
    }
}