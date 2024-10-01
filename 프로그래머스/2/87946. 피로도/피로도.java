import java.util.*;

class Solution {
    
    int[][] dun;
    int k;
    boolean[] visit;
    int answer;
    
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dun = dungeons;
        this.visit = new boolean[dungeons.length];

        answer = -1;
        dfs(0, k);
        
        return answer;
    }
    
    void dfs(int count, int rest) {
        answer = Math.max(answer, count);
        
        for (int i = 0; i < dun.length; i++) {
            if (!visit[i] && rest >= dun[i][0]) {
                visit[i] = true;
                dfs(count + 1, rest - dun[i][1]);
                visit[i] = false;
            }
        }
    }
}