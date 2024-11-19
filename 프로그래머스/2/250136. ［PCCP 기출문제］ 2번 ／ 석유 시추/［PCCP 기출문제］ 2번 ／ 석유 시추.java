import java.util.*;
class Solution {
    
    // 0 : 빈 땅, 1 : 석유
    // 최대 100 * 100
    int[][] land;
    boolean[][] visited;
    Map<Integer, Integer> map;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int R, C;
    int answer;
    public int solution(int[][] land) {
        this.R = land.length;
        this.C = land[0].length;
        this.land = land;
        this.visited = new boolean[R][C];
        this.map = new HashMap<>();
        
        // for (int r = 0; r < R; r++) {
        //     for (int c = 0; c < C; c++) {
        //         System.out.print(land[r][c] + " ");
        //     }
        //     System.out.println();
        // }
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (land[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c);
                }
            }
        }
        
        answer = 0;
        // for (int key : map.keySet()) {
        //     System.out.println("key : " + key + " size : " + map.get(key));
        // }
        for (int value : map.values()) {
            answer = Math.max(answer, value);
        }
        
        return answer;
    }
    
    void bfs(int r, int c) {
        visited[r][c] = true;
        int size = 1;
        
        Set<Integer> set = new HashSet<>();
        set.add(c);
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];
                
                if (isValid(nr, nc)) {
                    visited[nr][nc] = true;
                    set.add(nc);
                    size++;
                    queue.add(new int[] {nr, nc});
                }
            }
        }
        
        column(set, size);
    }
    
    void column(Set<Integer> set, int size) {
        for (int col : set) {
            map.put(col, map.getOrDefault(col, 0) + size);
        }
    }
    
    boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C && !visited[r][c]
            && land[r][c] == 1;
    }
}