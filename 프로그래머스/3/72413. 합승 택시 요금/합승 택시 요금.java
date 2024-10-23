import java.util.*;
class Solution {
    
    final int INF = 999999;
    int[][] distance;
    int answer = Integer.MAX_VALUE;
    int n, s, a, b;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.s = s - 1;
        this.a = a - 1;
        this.b = b - 1;
        
        distance = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == c) {
                    distance[r][c] = 0;
                } else {
                    distance[r][c] = INF;
                }
            }
        }
        
        for (int i = 0; i < fares.length; i++) {
            int A = fares[i][0] - 1;
            int B = fares[i][1] - 1;
            int C = fares[i][2];
            
            distance[A][B] = C;
            distance[B][A] = C;
        }
        
        floyd();
        
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, distance[this.s][i] + distance[i][this.a] + distance[i][this.b]);
        }
        
        return answer;
    }
    
    void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }
}















