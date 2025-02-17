import java.util.Scanner;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        dp = new int[11][N + 2];

        for (int r = 1; r <= 10; r++) {
            dp[r][1] = 1;
        }

        for (int r = 1; r <= 10; r++) {
            for (int c = 2; c < N + 2; c++) {
                dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % 10007;
            }
        }

        System.out.println(dp[10][N + 1]);
    }
}