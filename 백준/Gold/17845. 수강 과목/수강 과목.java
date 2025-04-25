import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] subjects = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            subjects[i][0] = Integer.parseInt(st.nextToken());
            subjects[i][1] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N + 1];
        for (int[] subject : subjects) {
            int W = subject[0];
            int T = subject[1];

            for (int i = N; i >= T; i--) {
                dp[i] = Math.max(dp[i], dp[i - T] + W);
            }
        }

        System.out.println(dp[N]);
    }
}