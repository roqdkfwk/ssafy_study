import java.io.*;
public class Main {

    static int N;
    static int[] wine;
    static Integer[] dp;
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    /**
     * N개의 포도주가 있을 때, 문제의 조건에 따라 최대의 포도주를 마시는 것은
     * N-2, N-1번째 포도주를 마시고 N번째 포도주를 마시지 않는 경우와
     * N-3, N-1, N번째 포도주를 마시는 경우
     * 둘 중에 한 가지 경우이다.
     */
    private static void Solution() {
        if (N <= 3) return;

        answer = recur(N - 1);
    }

    private static int recur(int num) {
        if(dp[num] == null) {
            dp[num] = Math.max(Math.max(recur(num - 2), recur(num - 3) + wine[num - 1]) + wine[num], recur(num - 1));
        }
        return dp[num];
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        wine = new int[N];
        dp = new Integer[N];

        if (N == 1) {
            answer = Integer.parseInt(br.readLine());
            return;
        }
        else if (N == 2) {
            answer = Integer.parseInt(br.readLine()) + Integer.parseInt(br.readLine());
            return;
        }
        else if (N == 3) {
            int wine1 = Integer.parseInt(br.readLine());
            int wine2 = Integer.parseInt(br.readLine());
            int wine3 = Integer.parseInt(br.readLine());

            answer = Math.max(wine1 + wine2, wine2 + wine3);
            answer = Math.max(answer, wine1 + wine3);
            return;
        }

        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = wine[0];
        dp[1] = dp[0] + wine[1];
        dp[2] = Math.max(wine[0] + wine[1], wine[1] + wine[2]);
        dp[2] = Math.max(dp[2], wine[0] + wine[2]);
    }
}