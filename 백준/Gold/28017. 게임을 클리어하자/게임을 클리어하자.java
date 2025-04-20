import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    static int N, M;
    static int[][] dp, weapons;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];
        weapons = new int[N][M];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                weapons[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < M; i++) {
            dp[0][i] = weapons[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) {
                    if (j == k) continue;

                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + weapons[i][j]);
                }
            }
        }

        Arrays.sort(dp[N - 1]);
        System.out.println(dp[N - 1][0]);
    }
}