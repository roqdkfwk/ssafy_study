import java.util.*;
import java.io.*;
public class Main {

    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(e.weight, this.weight);
        }
    }

    static int N, M, S, E;
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
        N = Integer.parseInt(st.nextToken());   // 집의 수
        M = Integer.parseInt(st.nextToken());   // 다리의 수

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());   // 출발 지점
        E = Integer.parseInt(st.nextToken());   // 도착 지점

        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            edges[A].add(new Edge(B, W));
            edges[B].add(new Edge(A, W));
        }
    }

    private static void solution() {
        int[] dp = new int[N + 1];
        dp[S] = Integer.MAX_VALUE;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 시작 위치에는 무한 개의 빼빼로를 가지고 있을 수 있다.
        pq.add(new Edge(S, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            for (Edge e : edges[curr.to]) {
                // 다음 위치까지 들고갈 수 있는 빼빼로의 개수가 적어지는 경우는 패스
                if (dp[e.to] >= Math.min(dp[curr.to], e.weight)) continue;
                dp[e.to] = Math.min(dp[curr.to], e.weight);
                pq.add(new Edge(e.to, dp[e.to]));
            }
        }

        answer = dp[E];
    }

    private static void printResult() {
        System.out.println(answer);
    }
}