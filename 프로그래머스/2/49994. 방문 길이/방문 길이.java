import java.util.*;
class Solution {
    
    int r, c;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    HashMap<String, Integer> hashMap;
    int answer;
    
    public int solution(String dirs) {
        this.hashMap = new HashMap<>();
        
        for (int n = 0; n < dirs.length(); n++) {
            move(dirs.charAt(n));
        }
        return hashMap.size();
    }
    
    void move(char direction) {
        // U : 1, D : 0, L : 2, R : 3
        int d = 0;
        if (direction == 'U') d = 1;
        else if (direction == 'D') d = 0;
        else if (direction == 'L') d = 2;
        else d = 3;
        
        int nr = r + dr[d];
        int nc = c + dc[d];
        
        if (nr > 5 || nr < -5 || nc > 5 || nc < -5) return;
        
        double cr = (double)(r + nr) / 2;
        double cc = (double)(c + nc) / 2;
        
        r = nr;
        c = nc;        
        hashMap.put(cr + "." + cc, 1);
    }
}