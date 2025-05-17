import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 커피
        int K = Integer.parseInt(st.nextToken());   // 카페인

        st = new StringTokenizer(br.readLine());
        int[] caffeine = new int[N];
        for (int i = 0; i < N; i++) {
            caffeine[i] = Integer.parseInt(st.nextToken());
        }

        // dp[k] = 카페인 k를 충족하기 위해 필요한 커피의 최소 개수
        int[] dp = new int[K + 1];
        Arrays.fill(dp, 10_000_001);
        dp[0] = 0;
        for (int 커피 = 0; 커피 < N; 커피++) {
            for (int 카페인 = K; 카페인 >= caffeine[커피]; 카페인--) {
                dp[카페인] = Math.min(dp[카페인], dp[카페인 - caffeine[커피]] + 1);
            }
        }

        System.out.println(dp[K] == 10_000_001 ? -1 : dp[K]);
    }
}