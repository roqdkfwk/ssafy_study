import java.util.*;
import java.io.*;
public class Main {

    static class Edge implements Comparable<Edge> {
        int from, to, time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.time, e.time);
        }
    }

    static int N, M;
    static List<Edge> edges;
    static StringBuilder answer = new StringBuilder();
    static final long INF = Long.MAX_VALUE >> 1;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시의 개수
        M = Integer.parseInt(st.nextToken());   // 노선의 개수

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())));
        }
    }

    private static void solution() {
        long[] distance = new long[N];
        Arrays.fill(distance, INF);
        distance[0] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edges) {
                if (distance[e.from] == INF || distance[e.to] <= distance[e.from] + e.time) continue;
                distance[e.to] = distance[e.from] + e.time;
            }
        }

        for (Edge e : edges) {
            if (distance[e.from] == INF || distance[e.to] <= distance[e.from] + e.time) continue;
            answer.append("-1");
            return;
        }

        for (int i = 1; i < N; i++) {
            answer.append(distance[i] == INF ? -1 : distance[i]).append("\n");
        }
    }

    private static void printResult() {
        System.out.println(answer.toString().trim());
    }
}