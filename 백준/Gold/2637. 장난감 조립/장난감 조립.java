import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M;
    static List<int[]>[] graph;
    static int[] indegree;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        indegree = new int[N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()); // 완성품
            int Y = Integer.parseInt(st.nextToken()); // 부품
            int K = Integer.parseInt(st.nextToken()); // 개수

            graph[Y].add(new int[]{X, K});
            indegree[X]++;
        }

        boolean[] isBasic = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                isBasic[i] = true;
            }
        }

        topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (isBasic[i] && dp[N][i] > 0) {
                sb.append(i).append(" ").append(dp[N][i]).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                dp[i][i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] edge : graph[curr]) {
                int next = edge[0];
                int need = edge[1];

                for (int i = 1; i <= N; i++) {
                    if (dp[curr][i] == 0) continue;
                    dp[next][i] += dp[curr][i] * need;
                }

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}
