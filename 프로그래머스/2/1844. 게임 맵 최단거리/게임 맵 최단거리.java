import java.util.*;

class Solution {
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    boolean[][] visit;
    int[][] distance;
    
    public int solution(int[][] maps) {
        
        visit = new boolean[maps.length][maps[0].length];
        distance = new int[maps.length][maps[0].length];
        distance[0][0] = 1;
        distance[maps.length - 1][maps[0].length - 1] = -1;
        
        BFS(maps, visit);
                
        return distance[maps.length - 1][maps[0].length - 1];             
    }   // solution
    
    public void BFS(int[][] maps, boolean[][] visit) {
        
        visit[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int rNow = now[0];
            int cNow = now[1];
            
            for (int i = 0; i < 4; i++) {
                int rNext = rNow + dr[i];
                int cNext = cNow + dc[i];
                
                if (!(rNext < 0 || rNext >= maps.length || cNext < 0 || cNext >= maps[0].length
            || maps[rNext][cNext] == 0 || visit[rNext][cNext])) {
                    distance[rNext][cNext] = distance[rNow][cNow] + 1;
                    visit[rNext][cNext] = true;
                    queue.add(new int[]{rNext, cNext});
                }
            }
        }   // while
    }   // BFS
}   















