import java.util.*;
class Solution {
    
    int N;
    int[][] board;
    boolean[][] visit; 
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int[][][] dp;
    int answer;
    
    public int solution(int[][] board) {
        answer = 987654321;
        this.N = board.length;
        this.board = board;
        this.visit = new boolean[N][N];
        this.dp = new int[4][N][N];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], 987654321);
            }
        }
        // for (int i = 0; i < 4; i++) {
        //     dp[i][0][0] = 0;
        // }
        
        visit[0][0] = true;
        for (int d = 0; d < 4; d++) {
            dfs(0, 0, d, 0);    // 행, 열, 방향, 비용
        }
        
        // for (int i = 0; i < 4; i++) {
        //     System.out.println("i : " + i);
        //     for (int j = 0; j < N; j++) {
        //         for (int k = 0; k < N; k++) {
        //             System.out.print(dp[i][j][k] + " ");
        //         }
        //         System.out.println();
        //     }
        // }
        
        return answer;
    }
    
    void dfs(int row, int col, int direction, int cost) {
        if (row == N - 1 && col == N - 1) {
            answer = Math.min(answer, cost);
            return;
        }
        
        if (dp[direction][row][col] <= cost) return;
            dp[direction][row][col] = cost;
        
        for (int i = 0; i < 4; i++) {
            int NR = row + dr[i];
            int NC = col + dc[i];
            
            if (!isOk(NR, NC)) continue;
            
            visit[NR][NC] = true;
            if (i == direction) {
                dfs(NR, NC, i, cost + 100);
            } else {
                dfs(NR, NC, i, cost + 600);
            }
            visit[NR][NC] = false;
        }
    }
    
    boolean isOk(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N && !visit[r][c] && board[r][c] == 0;
    }
}


























