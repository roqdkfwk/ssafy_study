import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[][] maze;
    static int[][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        maze = new int[N][N];   // 미로
        dp = new int[N][N];     // 부숴야하는 방의 개수
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < N; c++) {
                maze[r][c] = str.charAt(c) - '0';
            }
            Arrays.fill(dp[r], 987654321);
        }
    }

    private static void solution() {
        dp[0][0] = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (dp[nr][nc] <= dp[r][c] + (1 - maze[r][c])) continue;
                dp[nr][nc] = dp[r][c] + (1 - maze[r][c]);

                if (maze[r][c] == 0) queue.add(new int[] {nr, nc});
                else queue.addFirst(new int[] {nr, nc});
            }
        }
    }

    private static void printResult() {
        System.out.println(dp[N - 1][N - 1]);
    }
}