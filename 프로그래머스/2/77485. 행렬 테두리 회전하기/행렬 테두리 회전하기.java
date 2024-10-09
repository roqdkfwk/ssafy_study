import java.util.*;
class Solution {
    
    int R1, C1, R2, C2;
    int[] dr = {0, 1, 0, -1};   // 우, 하, 좌, 상
    int[] dc = {1, 0, -1, 0};   // 우, 하, 좌, 상
    Queue<Integer> queue;
    int[][] matrix;
    boolean[][] visit;
    int min;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        matrix = new int[rows][columns];
        int num = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = num++;
            }
        }
        
        queue = new LinkedList<>();
        for (int i = 0; i < queries.length; i++) {
            visit = new boolean[rows][columns];
            this.R1 = queries[i][0] - 1;
            this.C1 = queries[i][1] - 1;
            this.R2 = queries[i][2] - 1;
            this.C2 = queries[i][3] - 1;
            
            min = 10000;
            visitArr();
            rotate(R1, C1, R2, C2);
            answer[i] = min;
            
            visitArr();
            mark(R1, C1, R2, C2);
        }
        
        return answer;
    }
    
    void mark(int R1, int C1, int R2, int C2) {
        int R = R1;
        int C = C1 + 1;
        
        int direction = 0;
        while (visit[R][C]) {
            visit[R][C] = false;
            matrix[R][C] = queue.poll();
            
            if (isOk(R + dr[direction % 4], C + dc[direction % 4])) {
                R += dr[direction];
                C += dc[direction];
            } else {
                direction++;
                R += dr[direction % 4];
                C += dc[direction % 4];
            }
        }
    }
    
    void rotate(int R1, int C1, int R2, int C2) {
        int R = R1;
        int C = C1;
        
        int direction = 0;
        while (visit[R][C]) {
            visit[R][C] = false;
            min = Math.min(min, matrix[R][C]);
            queue.add(matrix[R][C]);
            
            if (isOk(R + dr[direction % 4], C + dc[direction % 4])) {
                R += dr[direction % 4];
                C += dc[direction % 4];
            } else {
                direction++;
                R += dr[direction % 4];
                C += dc[direction % 4];
            }
        }
    }
    
    boolean isOk(int row, int col) {
        return row >= R1 && row <= R2 && col >= C1 && col <= C2 && visit[row][col];
    }
    
    void visitArr() {
        for (int r = R1 + 1; r < R2; r++) {
            visit[r][C1] = visit[r][C2] = true;
        }
        for (int c = C1; c <= C2; c++) {
            visit[R1][c] = visit[R2][c] = true;
        }
    }
}