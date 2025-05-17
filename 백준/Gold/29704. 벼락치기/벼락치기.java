import java.util.*;
import java.io.*;
public class Main {

    /**
     * 문제를 중복으로 해결할 수는 없으므로 역방향
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int answer = 0;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 문제
        int T = Integer.parseInt(st.nextToken());   // 남은 기한

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            answer += arr[i][1];
        }

        // 해결할 수 있는 문제의 벌금 합의 최대
        int[] dp = new int[T + 1];
        for (int problem = 0; problem < N; problem++) {
            for (int i = T; i >= arr[problem][0]; i--) {
                // 과제 제출 기한이 i일 남았을 때 해결할 수 있는 문제의 벌금 합의 최대
                dp[i] = Math.max(dp[i], dp[i - arr[problem][0]] + arr[problem][1]);
            }
        }

        answer -= dp[T];
        System.out.println(answer);
    }
}