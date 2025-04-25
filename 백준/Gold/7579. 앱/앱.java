import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        int[] cost = new int[N];
        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i];
        }

        int[] dp = new int[maxCost + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int A = memory[i];
            int B = cost[i];

            for (int j = maxCost; j >= B; j--) {
                if (dp[j - B] == -1) continue;

                dp[j] = Math.max(dp[j], dp[j - B] + A);
            }
        }

        int answer = 0;
        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= M) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}