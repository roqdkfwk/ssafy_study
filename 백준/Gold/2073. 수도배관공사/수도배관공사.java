import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /**
     * 길이가 D일 때 용량의 최대
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int maxLength = 0;
        int[][] pipes = new int[P][2];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i][0] = Integer.parseInt(st.nextToken()); // L
            pipes[i][1] = Integer.parseInt(st.nextToken()); // C
        }

        // dp[길이] = 최대 용량
        int[] dp = new int[D + 1];
        Arrays.fill(dp, -1);
        dp[0] = Integer.MAX_VALUE;

        for (int i = 0; i < P; i++) {
            int L = pipes[i][0];
            int C = pipes[i][1];

            for (int j = D; j >= L; j--) {
                if (dp[j - L] == -1) continue;

                dp[j] = Math.max(dp[j], Math.min(dp[j - L], C));
            }
        }

        System.out.println(dp[D]);
    }
}