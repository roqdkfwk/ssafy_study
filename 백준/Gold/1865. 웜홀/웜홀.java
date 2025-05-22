import java.util.*;
import java.io.*;
public class Main {

    static class Edge {
        int from, to, time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    static int T, N, M, W;
    static List<Edge> edges;
    static StringBuilder answer = new StringBuilder();
    static final int INF = 987654321;

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
            N = Integer.parseInt(st.nextToken());   // 정점
            M = Integer.parseInt(st.nextToken());   // 도로
            W = Integer.parseInt(st.nextToken());   // 웜홀

            edges = new ArrayList<>();
            // 도로의 정보
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                edges.add(new Edge(A, B, C));
                edges.add(new Edge(B, A, C));
            }

            // 웜홀의 정보
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                edges.add(new Edge(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        -Integer.parseInt(st.nextToken())));
            }

            solution();
        }
    }

    private static void solution() {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, 0);

        for (int i = 1; i <= N; i++) {
            for (Edge e : edges) {
                if (distance[e.to] <= distance[e.from] + e.time) continue;

                distance[e.to] = distance[e.from] + e.time;
                if (i == N) {
                    answer.append("YES").append("\n");
                    return;
                }
            }
        }

        answer.append("NO").append("\n");
    }

    private static void printResult() {
        System.out.println(answer.toString().trim());
    }
}