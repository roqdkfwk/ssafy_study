import java.util.*;
class Solution {
    
    boolean[][] chess;
    int answer;
    int n;
    
    public int solution(int n) {
        this.n = n;
        chess = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            chess[0][i] = true;
            dfs(0, i);
            chess[0][i] = false;
            System.out.println();
        }
        
        return answer;
    }
    
    void dfs(int row, int col) {    // 시작 위치의 행, 렬
        // 열 검사
        for (int r = 0; r < row; r++) {
            if (chess[r][col]) {    // (r, col)이 방문한 위치라면 현재 위치는 퀸을 놓을 수 없음
                return;
            }
        }
        // 대각선 검사
        int R = row;
        int C = col;
        while (R > 0 && C > 0) {    // 좌상 대각선
            R--;
            C--;
            if (chess[R][C]) {
                return;
            }
        }
        R = row;
        C = col;
        while (R > 0 && C < n - 1) {// 우상 대각선
            R--;
            C++;
            if (chess[R][C]) {
                return;
            }
        }
        
        // 현재 위치가 퀸을 놓을 수 있는 자리인데
        // 마지막 줄인 경우
        if (row == n - 1) {
            answer++;
            return;
        }
        // 마지막 줄이 아닌 경우 다음 위치 탐색
        for (int i = 0; i < n; i++) {
            chess[row + 1][i] = true;
            dfs(row + 1, i);
            chess[row + 1][i] = false;
        }
    }
}





























