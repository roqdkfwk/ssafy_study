import java.util.*;
import java.io.*;
public class Main {

    /**
     * (r, c)에서 목표 지점까지 n개의 경로가 있을 때
     * 다른 경로를 통해 (r, c)에 도착하면 그 이후는 계산할 필요가 없다. >> 메모이제이션 사용
     * dp[r][c] = (r, c)에서 도착 지점까지 갈 수 있는 경로의 수
     */

    static int R, C;
    static int[][] grid, dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new int[R][C];
        dp = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
                dp[r][c] = -1;
            }
        }
    }

    private static void solution() {
        dfs(0, 0);
    }

    private static int dfs(int r, int c) {
        // 목표 지점에 도달하면 경로 하나 완성
        if (r == R - 1 && c == C - 1) return 1;

        // 이미 방문한 곳이면 저장된 값을 반환
        if (dp[r][c] != -1) return dp[r][c];

        // 현재 위치에서 출발하는 경로 개수 초기화
        dp[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 유효한 범위 내에서, 현재 위치보다 낮은 곳으로만 이동 가능
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] < grid[r][c]) {
                dp[r][c] += dfs(nr, nc);
            }
        }
        return dp[r][c];
    }

    private static void printResult() {
        System.out.println(dp[0][0]);
    }
}