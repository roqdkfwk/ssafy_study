import java.util.*;
import java.io.*;

public class Main {

    static int R, C;    // R : 행, C : 열
    static int N;       // N : 시간
    static char[][] grid, answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        grid = new char[R][C];
        for (int r = 0; r < R; r++)
            grid[r] = br.readLine().toCharArray();

        answer = Solution(grid, N);

        printResult(answer);
    }

    static void printResult(char[][] Answer) {
        for (int r = 0; r < Answer.length; r++) {
            for (int c = 0; c < Answer[0].length; c++) {
                System.out.print(Answer[r][c]);
            }
            System.out.println();
        }
    }

    static char[][] Solution(char[][] grid, int time) {
        if (time == 1) return grid;

        char[][] fullGrid = new char[R][C];
        for (int r = 0; r < R; r++) Arrays.fill(fullGrid[r], 'O');

        if (time % 2 == 0) return fullGrid;

        // 폭탄의 상태는 4초 주기로 반복
        char[][] firstBomb = bomb(grid);     
        char[][] secondBomb = bomb(firstBomb);

        return (time % 4 == 3) ? firstBomb : secondBomb;
    }

    static char[][] bomb(char[][] grid) {
        R = grid.length;
        C = grid[0].length;
        char[][] grid2 = new char[R][C];
        boolean[][] visited = new boolean[R][C];

        // 현재 폭탄과 인접한 칸을 방문 처리
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'O') {
                    visited[r][c] = true;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (isInside(nr, nc)) {
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                grid2[r][c] = visited[r][c] ? '.' : 'O';
            }
        }

        return grid2;
    }

    static boolean isInside(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}