import java.util.*;
import java.io.*;
public class Main {

    static int R, C;
    static int[][] mars, dp;

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

        mars = new int[R][C];
        dp = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                mars[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = mars[0][0];
        for (int c = 1; c < C; c++) {
            dp[0][c] = dp[0][c - 1] + mars[0][c];
        }
    }

    private static void solution() {
        for (int r = 1; r < R; r++) {
            int[] leftToRight = new int[C];
            int[] rightToLeft = new int[C];

            leftToRight[0] = dp[r - 1][0] + mars[r][0];
            for (int c = 1; c < C; c++) {
                leftToRight[c] = Math.max(leftToRight[c - 1], dp[r - 1][c]) + mars[r][c];
            }

            rightToLeft[C - 1] = dp[r - 1][C - 1] + mars[r][C - 1];
            for (int c = C - 2; c >= 0; c--) {
                rightToLeft[c] = Math.max(rightToLeft[c + 1], dp[r - 1][c]) + mars[r][c];
            }

            for (int c = 0; c < C; c++) {
                dp[r][c] = Math.max(leftToRight[c], rightToLeft[c]);
            }
        }
    }

    private static void printResult() {
        System.out.println(dp[R - 1][C - 1]);
    }
}