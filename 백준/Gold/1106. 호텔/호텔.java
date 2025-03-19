import java.util.*;
import java.io.*;
public class Main {

    static int C, N;
    static int[][] arr;
    static int[] dp;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solution() {
        dp = new int[C + 101];          // 여유 공간을 두어, C 이상도 탐색 가능
        Arrays.fill(dp, 1000000);   // 최소 비용을 찾기 위해 큰 값으로 초기화
        dp[0] = 0;                      // 0명을 확보하는 최소 비용은 0원

        for (int i = 0; i < N; i++) {
            int cost = arr[i][0];
            int customer = arr[i][1];

            for (int j = customer; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + cost);
            }
        }

        answer = Integer.MAX_VALUE;
        for (int i = C; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }
    }

    private static void printResult() {
        System.out.println(answer);
    }
}