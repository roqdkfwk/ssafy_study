import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static double[][] W;
    static double[][] dp;
    static final double INF = 1e9;
    static double[] r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        r = new double[N];
        c = new double[N];

        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            r[i] = Double.parseDouble(st.nextToken());
            c[i] = Double.parseDouble(st.nextToken());
        }

        // 거리 계산
        W = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                	W[i][j] = 0;
                } else {
                    double dr = r[i] - r[j];
                    double dc = c[i] - c[j];
                    W[i][j] = Math.sqrt(dr * dr + dc * dc);
                }
            }
        }

        dp = new double[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1.0);
        }

        double answer = tsp(0, 1 << 0);
        System.out.printf("%.6f\n", answer);
    }

    private static double tsp(int prev, int visited) {
        if (visited == (1 << N) - 1) {
            return W[prev][0];
        }

        if (dp[prev][visited] != -1.0) {
            return dp[prev][visited];
        }

        double minCost = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;

            double cost = W[prev][next] + tsp(next, visited | (1 << next));
            minCost = Math.min(minCost, cost);
        }

        dp[prev][visited] = minCost;
        return minCost;
    }
}
