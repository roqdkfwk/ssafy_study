import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int T;
    static int N;
    static int[][] score;
    static int[][] dp;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            score = new int[2][N + 1];
            dp = new int[2][N + 1];
            
            for (int i = 0; i < 2; i++) {
                
            	st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) score[i][j] = Integer.parseInt(st.nextToken());
            }

            dp[0][1] = score[0][1];    // 첫번째 col은 자기 자신이 최대이므로 자기 자신으로 초기화
            dp[1][1] = score[1][1];

            for (int j = 2; j <= N; j++) {
                
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + score[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + score[1][j];
            }

            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }    // t에 대한 for문
    }
}