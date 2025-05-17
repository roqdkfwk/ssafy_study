import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(N);

        int answer = 0;
        int[] dp = new int[100_001];
        Arrays.fill(dp, 987654321);
        dp[N] = 0;

        while (!queue.isEmpty()) {
            int X = queue.poll();

            if (X == K) answer++;

            if (X - 1 >= 0 && dp[X - 1] >= dp[X] + 1) {
                dp[X - 1] = dp[X] + 1;
                queue.add(X - 1);
            }
            if (X + 1 >= 0 && X + 1 <= 100_000 && dp[X + 1] >= dp[X] + 1) {
                dp[X + 1] = dp[X] + 1;
                queue.add(X + 1);
            }
            if (2 * X <= 100_000 && dp[2 * X] >= dp[X] + 1) {
                dp[2 * X] = dp[X] + 1;
                queue.add(2 * X);
            }
        }

        System.out.println(dp[K] + "\n" + answer);
    }
}