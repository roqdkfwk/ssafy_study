import java.util.*;
import java.io.*;
public class Main {

    static int N, L;
    static int[][] grid;
    static boolean[][] rowVisited, colVisited;
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void Solution() {
        for (int i = 0; i < N; i++) {
            if (searchRow(i)) answer++;
            if (searchColumn(i)) answer++;
        }
    }

    private static boolean searchRow(int row) {
        int height = grid[row][0];

        for (int c = 1; c < N; c++) {
            if (height == grid[row][c]) continue;
            if (Math.abs(height - grid[row][c]) > 1) return false;    // 높이가 2 이상 차이나는 경우 경사로를 놓을 수 없으므로 탐색을 그만한다.
            if (height - grid[row][c] == 1) {
                if (!lowerThan(row, c, 0)) return false;
            }

            if (height - grid[row][c] == -1) {
                if (!higherThan(row, c - 1, 0)) return false;
            }
            height = grid[row][c];
        }
        return true;
    }

    private static boolean searchColumn(int col) {
        int height = grid[0][col];

        for (int r = 1; r < N; r++) {
            if (height == grid[r][col]) continue;
            if (Math.abs(height - grid[r][col]) > 1) return false;    // 높이가 2 이상 차이나는 경우 경사로를 놓을 수 없으므로 탐색을 그만한다.
            if (height - grid[r][col] == 1) {
                if (!lowerThan(col, r, 1)) return false;
            }

            if (height - grid[r][col] == -1) {
                if (!higherThan(col, r - 1, 1)) return false;
            }
            height = grid[r][col];
        }
        return true;
    }

    private static boolean higherThan(int line, int start, int direction) {
        int length = 1;

        // 수평 이동
        if (direction == 0) {
            int col = start;
            if (rowVisited[line][col]) return false;

            while (col > 0 && grid[line][col] == grid[line][col - 1] && length < L) {
                length++;
                col--;
                if (rowVisited[line][col]) return false;
            }

            if (length >= L) {
                for (int i = 0; i < L; i++) {
                    rowVisited[line][start - i] = true;
                }
                return true;
            }
        }
        // 수직 이동
        else {
            int row = start;
            if (colVisited[row][line]) return false;

            while (row > 0 && grid[row][line] == grid[row - 1][line] && length < L) {
                length++;
                row--;
                if (colVisited[row][line]) return false;
            }

            if (length >= L) {
                for (int i = 0; i < L; i++) {
                    colVisited[start - i][line] = true;
                }
                return true;
            }
        }
        return false;
    }

    private static boolean lowerThan(int line, int start, int direction) {
        int length = 1;

        // 수평 이동
        if (direction == 0) {
            int col = start;
            if (rowVisited[line][col]) return false;

            while (col < N - 1 && grid[line][col] == grid[line][col + 1] && length < L) {
                length++;
                col++;
                if (rowVisited[line][col]) return false;
            }

            if (length >= L) {
                for (int i = 0; i < L; i++) {
                    rowVisited[line][start + i] = true;
                }
                return true;
            }
        }
        // 수직 이동
        else {
            int row = start;
            if (colVisited[row][line]) return false;

            while (row < N - 1 && grid[row][line] == grid[row + 1][line] && length < L) {
                length++;
                row++;
                if (colVisited[row][line]) return false;
            }

            if (length >= L) {
                for (int i = 0; i < L; i++) {
                    colVisited[start + i][line] = true;
                }
                return true;
            }
        }
        return false;
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        rowVisited = new boolean[N][N];
        colVisited = new boolean[N][N];
    }
}