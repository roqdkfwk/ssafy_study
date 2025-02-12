import java.io.*;
public class Main {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        dp[0] = 0;
        dp[1] = 1;

        if (N == 1) {
            System.out.println(1);
            return;
        }

        dp[N] = recur(N);
        System.out.println(dp[N]);
    }

    private static long recur(int num) {
        if (num <= 1) {
            return num;
        }
        if (dp[num] == 0) {
            dp[num] = recur(num - 1) + recur(num - 2);
            return dp[num];
        }
        return dp[num];
    }
}