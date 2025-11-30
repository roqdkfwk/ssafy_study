import java.util.*;
import java.io.*;

public class Main {
    
    static int N, K;
    static int[][] walk, bike;
    // dp[i][t] : i번째 도시까지 이동했을 때, 총 t분이 걸린 경우의 최대 모금액
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        walk = new int[N + 1][2];
        bike = new int[N + 1][2];
        dp = new int[N + 1][K + 1];
        
        // [수정 1] DP 배열 초기화: -1은 도달 불가능을 의미
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // [수정 2] 시작점 초기화: 0번 도시(출발 전), 0분 소요, 0원 획득
        dp[0][0] = 0;
        
        for (int i = 1; i <= N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            walk[i][0] = tmp[0]; walk[i][1] = tmp[1];
            bike[i][0] = tmp[2]; bike[i][1] = tmp[3];
        }
        
        // DP 수행
        for (int i = 1; i <= N; i++) {
            int w_time = walk[i][0];
            int w_money = walk[i][1];
            int b_time = bike[i][0];
            int b_money = bike[i][1];
            
            // 시간(time)을 0부터 K까지 순회하며 가능한 경우를 채움
            for (int time = 0; time <= K; time++) {
                
                // [수정 3] 도보 선택: 시간이 범위 내이고, 이전 도시까지 해당 시간(time - w_time)에 도착한 적이 있어야 함
                if (time >= w_time && dp[i - 1][time - w_time] != -1) {
                    dp[i][time] = Math.max(dp[i][time], dp[i - 1][time - w_time] + w_money);
                }
                
                // [수정 4] 자전거 선택: 위와 동일
                if (time >= b_time && dp[i - 1][time - b_time] != -1) {
                    dp[i][time] = Math.max(dp[i][time], dp[i - 1][time - b_time] + b_money);
                }
            }
        }
        
        // 정답 출력: N번째 도시에 도착했을 때, K시간 이내인 모든 경우 중 최댓값
        int answer = 0;
        for (int i = 0; i <= K; i++) {
            answer = Math.max(answer, dp[N][i]);
        }
        
        System.out.println(answer);
    }
}