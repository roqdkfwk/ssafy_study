import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;    // 도시의 수
    static int[][] cost;    // 비용
    static boolean[] visit;    // 방문처리
    static int ans = Integer.MAX_VALUE;    // 정답
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][N + 1];
        visit = new boolean[N + 1];
        for (int r = 1; r < N + 1; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < N + 1; c++) {
                cost[r][c] = Integer.parseInt(st.nextToken());
            }
        }
            
        visit[1] = true;
        DFS(1, 1, 0, 0);
        
        System.out.println(ans);
    }    // main

    // 시작, 현재, 비용, 이동 횟수
    private static void DFS(int start, int current, int sum, int count) {
    	
        if (count == N - 1) {
            if (cost[current][start] != 0) {
                sum += cost[current][start];
                if (ans > sum)
                    ans = sum;
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visit[i] && cost[current][i] != 0) {
                visit[i] = true;
                DFS(start, i, sum + cost[current][i], count + 1);
                visit[i] = false;
            }
        }
    }    // DFS
}