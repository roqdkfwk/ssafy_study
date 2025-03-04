import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[] farm1, farm2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        farm1 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            farm1[i] = sc.nextInt();
        }

        farm2 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            farm2[i] = sc.nextInt();
        }

        dp = new int[N + 1][N + 1];
    }

    private static void solution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (Math.abs(farm1[i] - farm2[j]) <= 4) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }

    private static void printResult() {
        System.out.println(dp[N][N]);
    }
}