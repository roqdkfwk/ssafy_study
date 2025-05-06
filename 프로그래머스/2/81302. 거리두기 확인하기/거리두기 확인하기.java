import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        char[][] room = new char[5][5];
        
        for (int t = 0; t < 5; t++) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    room[r][c] = places[t][r].charAt(c);
                }
            }
            
            answer[t] = roomStatus(room);
        }
        
        return answer;
    }
    
    int roomStatus(char[][] room) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        boolean[][] visited = new boolean[5][5];
        Deque<int[]> queue = new ArrayDeque<>();
        
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (room[r][c] != 'P') continue;
                
                queue = new ArrayDeque<>();
                queue.addLast(new int[] {r, c});
                visited = new boolean[5][5];
                visited[r][c] = true;
                
                for (int i = 0; i < 2; i++) {
                    int size = queue.size();
                    for (int j = 0; j < size; j++) {
                        int[] curr = queue.pollFirst();
                        for (int d = 0; d < 4; d++) {
                            int nr = curr[0] + dr[d];
                            int nc = curr[1] + dc[d];
                            
                            if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc] || room[nr][nc] == 'X') continue;
                            if (room[nr][nc] == 'P') return 0;
                            
                            queue.addLast(new int[] {nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
        }
        return 1;
    }
}




















