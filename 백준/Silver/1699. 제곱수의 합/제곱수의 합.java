import java.io.*;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(dp[N]);
    }

    private static void Solution() {
        // 제곱수이면 1을 출력
        if (Math.sqrt(N) % 1 == 0) {
            dp[N] = 1;
            return;
        }
        // 제곱수가 아닌 경우
        dp[N] = recur(N);
    }

    private static int recur(int num) {
        if (Math.sqrt(num) % 1 == 0) {
            return 1;
        }

        if (dp[num] != 0) {
            return dp[num];
        }

        dp[num] = Integer.MAX_VALUE;
        for (int i = 1; i * i <= num; i++) {
            dp[num] = Math.min(dp[num], recur(num - i * i) + 1);
        }
        return dp[num];
    }

    private static void InputHandler() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        dp = new int[N + 1];
    }
}