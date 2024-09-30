class Solution {
    
    Boolean[][] grid;
    boolean[][] visit;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int IR, IC;
    int answer;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        this.IR = itemX * 2;
        this.IC = itemY * 2;
        answer = Integer.MAX_VALUE / 2;
        int count = rectangle.length;
        grid = new Boolean[101][101];
        
        // 1. 전체 사각형을 받고
        for (int i = 0; i < count; i++) {
            // 2. 좌표를 2배만큼 늘린 다음
            int R1 = rectangle[i][0] * 2;
            int C1 = rectangle[i][1] * 2;
            int R2 = rectangle[i][2] * 2;
            int C2 = rectangle[i][3] * 2;
            
            // 3. 테두리는 true, 내부는 false, 바깥은 null
            for (int r = R1; r <= R2; r++) {
                for (int c = C1; c <= C2; c++) {
                    if (r != R1 && r != R2 && c != C1 && c != C2) {
                        grid[r][c] = false;
                    } else {
                        if (grid[r][c] == null) {
                            grid[r][c] = true;
                        }
                    }
                }
            }
        }
        
        // 4. true를 따라서 dfs 2번
        int CR = characterX * 2, CC = characterY * 2;
        int IR = itemX * 2, IC = itemY * 2;
        visit = new boolean[101][101];
        dfs(CR, CC, 0);
        
        // 5. 두 경로 중 짧은 경로 구하고 길이를 반으로 줄임
        
        return answer / 2;
    }
    
    public void dfs(int CR, int CC, int length) {
        if (CR == IR && CC == IC) {
            answer = Math.min(answer, length);
            return;
        }
        
        visit[CR][CC] = true;
        
        for (int i = 0; i < 4; i++) {
            int NR = CR + dr[i];
            int NC = CC + dc[i];
            
            // 다음 경로가 테두리이고 && 방문하지 않은 지점이라면
            if (NR >= 0 && NR <= 100 && NC >= 0 && NC <= 100 && grid[NR][NC] != null 
                && grid[NR][NC] && !visit[NR][NC]) {
                dfs(NR, NC, length + 1);
            }
        }
        
        return;
    }
}























