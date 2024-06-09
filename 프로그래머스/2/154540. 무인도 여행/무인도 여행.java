import java.util.*;
import java.io.*;

class Solution {
    
    static char[][] map;
    static boolean[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public List<Integer> solution(String[] maps) {
        
        // 섬의 개수만큼 입력받을거니 List타입으로 answer 선언
        List<Integer> answer = new ArrayList<>();
        map = new char[maps.length][maps[0].length()];
        visit = new boolean[maps.length][maps[0].length()];
        
        for (int r = 0; r < maps.length; r++) 
            map[r] = maps[r].toCharArray();
        
        for (int r = 0; r < maps.length; r++) {
            for (int c = 0; c < maps[0].length(); c++) {
                if (!visit[r][c] && map[r][c] != 'X')
                    answer.add(BFS(new int[]{r, c}));
            }
        }   // for
        
        // 섬이 하나도 없어서 answer의 크기가 0인 경우
        if (answer.size() == 0)
            answer.add(-1);
        
        Collections.sort(answer);
        
        return answer;
    }
    
    public int BFS(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        
        visit[start[0]][start[1]] = true;
        int sum = 0;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int rNow = now[0];
            int cNow = now[1];
            sum += map[rNow][cNow] - '0';   // 식량만큼 sum에 더함
            
            for (int i = 0; i < 4; i++) {
                int rNext = rNow + dr[i];
                int cNext = cNow + dc[i];
                
                if (rNext >= 0 && rNext < map.length && cNext >= 00 && cNext < map[0].length
                    && !visit[rNext][cNext] && map[rNext][cNext] != 'X') {
                    queue.add(new int[]{rNext, cNext});
                    visit[rNext][cNext] = true;
                }
            }
        }   // while
        
        return sum;
    }   // BFS
}














