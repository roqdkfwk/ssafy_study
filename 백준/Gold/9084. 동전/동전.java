import java.util.*;
import java.io.*;
public class Main {

    static int T, N;
    static int[] coins; // 동전 종류
    static int target;  // 만들 금액
    static ArrayList<Integer> results; // 각 테스트 케이스의 결과 저장
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        results = new ArrayList<>();
    }

    private static void solution() throws IOException {
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            coins = new int[N];
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            target = Integer.parseInt(br.readLine());

            int[] dp = new int[target + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int j = coin; j <= target; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            results.add(dp[target]);
        }
    }

    private static void printResult() {
        for (int res : results) {
            System.out.println(res);
        }
    }
}