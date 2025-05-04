import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] dp = new int[X + 1];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for (int x = X; x >= 0; x--) {
                if (dp[x] == 0) continue;

                for (int c = 1; c <= C; c++) {
                    if (x + L * c > X) break;

                    dp[x + L * c] += dp[x];
                }
            }
        }

        System.out.println(dp[X]);
    }
}