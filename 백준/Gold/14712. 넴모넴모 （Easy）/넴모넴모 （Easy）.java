import java.util.*;
import java.io.*;
public class Main {

    static int R, C;
    static boolean[][] grid;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new boolean[R][C];
    }

    private static void solution() {
        nemmo(0, 0);
    }

    private static void nemmo(int r, int c) {
        if (c >= C) {
            nemmo(r + 1, 0);
            return;
        }

        if (r >= R) {
            answer++;
            return;
        }

        // (r, c)를 넴모로 채우지 않는 경우
        nemmo(r, c + 1);

        // (r, c)를 넴모로 채우는 경우
        if (!canPlaceNemmo(r, c)) return;

        grid[r][c] = true;
        nemmo(r, c + 1);
        grid[r][c] = false;
    }

    private static boolean canPlaceNemmo(int r, int c) {
        if (r == 0 || c == 0) {
            return true;
        }

        if (grid[r - 1][c - 1] && grid[r - 1][c] && grid[r][c - 1]) {
            return false;
        }
        return true;
    }

    private static void printResult() {
        System.out.println(answer);
    }
}