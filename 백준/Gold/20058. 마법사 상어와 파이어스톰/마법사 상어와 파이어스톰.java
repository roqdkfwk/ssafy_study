import java.io.*;
import java.util.*;

public class Main {

    static int N, Q;
    static int[][] A;
    static int[] L;
    static int SUM;
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
        N = Integer.parseInt(st.nextToken() );
        Q = Integer.parseInt(st.nextToken());

        int W = (int)Math.pow(2, N);
        A = new int[W][W];
        for (int r = 0; r < W; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < W; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
                SUM += A[r][c];
            }
        }

        L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }
    }

    /**
     * 1. 격자 하나의 크기 : 2^N
     * 2. 격자를 시계 방향으로 90도 회전
     * 3. 얼음이 있는 칸 2개 이하와 인접한 경우 얼음의 양 감소
     */
    private static void solution() {
        for (int q = 0; q < Q; q++) {
            int width = (int)Math.pow(2, L[q]);     // 격자 하나의 크기
            int T = (int)Math.pow(2, N) / width;    // 한 줄에 포함된 격자의 개수

            for (int i = 0; i < T; i++) {
                for (int c = 0; c < T; c++) {
                    rotateGrid(A, width * i, width * c, width);
                }
            }

            meltIce(A);
        }

        int W = (int)Math.pow(2, N);
        boolean[][] visited = new boolean[W][W];
        for (int r = 0; r < W; r++) {
            for (int c = 0; c < W; c++) {
                if (A[r][c] == 0 || visited[r][c]) continue;

                visited[r][c] = true;
                answer = Math.max(answer, dfs(A, r, c, visited));
            }
        }
    }

    /**
     * @param grid : 회전시킬 격자
     * @param R : 시작행
     * @param C : 시작열
     * @param W : 격자 한 변의 길이
     */
    private static void rotateGrid(int[][] grid, int R, int C, int W) {
        int[][] copy = new int[W][W];

        // 상 -> 우
        int cycle = 0;
        for (int r = R; r < R + W / 2; r++) {
            int index = 0;
            for (int c = C + cycle; c < C + W - cycle; c++) {
                copy[index + cycle][W - 1 - cycle] = grid[r][c];
                index++;
            }
            cycle++;
        }

        // 우 -> 하
        cycle = 0;
        for (int c = C + W - 1; c >= C + W / 2; c--) {
            int index = 0;
            for (int r = R + cycle; r < R + W - cycle; r++) {
                copy[W - 1 - cycle][W - 1 - index - cycle] = grid[r][c];
                index++;
            }
            cycle++;
        }

        // 하 -> 좌
        cycle = 0;
        for (int r = R + W - 1; r >= R + W / 2; r--) {
            int index = 0;
            for (int c = C + W - 1 - cycle; c >= C + cycle; c--) {
                copy[W - 1 - index - cycle][cycle] = grid[r][c];
                index++;
            }
            cycle++;
        }

        // 좌 -> 상
        cycle = 0;
        for (int c = C; c < C + W / 2; c++) {
            int index = 0;
            for (int r = R + W - 1 - cycle; r >= R + cycle; r--) {
                copy[cycle][index + cycle] = grid[r][c];
                index++;
            }
            cycle++;
        }

        for (int r = 0; r < W; r++) {
            for (int c = 0; c < W; c++) {
                grid[R + r][C + c] = copy[r][c];
            }
        }
    }

    private static void meltIce(int[][] grid) {
        Deque<int[]> queue = new ArrayDeque<>();

        int R = grid.length, C = grid.length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] != 0 && !checkIce(r, c, grid)) {
                    queue.add(new int[] {r, c});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            grid[curr[0]][curr[1]]--;
            SUM--;
        }
    }

    private static boolean checkIce(int r, int c, int[][] grid) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid.length)
                continue;

            if (grid[nr][nc] != 0) count++;
        }

        return count >= 3 ? true : false;
    }

    private static int dfs(int[][] grid, int R, int C, boolean[][] visited) {
        int W = grid.length;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {R, C});

        int count = 0;
        visited[R][C] = true;
        while (!queue.isEmpty()) {
            count++;
            int[] curr = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (nr < 0 || nr >= W || nc < 0 || nc >= W || visited[nr][nc] || grid[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.addLast(new int[] {nr, nc});
            }
        }

        return count;
    }

    private static void printResult() {
        System.out.println(SUM);
        System.out.println(answer);
    }
}