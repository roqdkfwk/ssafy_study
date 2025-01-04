import java.util.*;
class Solution {
    
    int r, c;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    HashMap<String, Integer> map1, map2;
    int answer;
    
    public int solution(String dirs) {
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
        
        for (int n = 0; n < dirs.length(); n++) {
            move(dirs.charAt(n));
        }
        return map1.size();
    }
    
    void move(char direction) {
        // U : 1, D : 1, L : 2, R : 3
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
        
        String key = cr + "." + cc;
        if (map1.containsKey(key)) map2.put(key, 1);
        else map1.put(key, 1);
    }
}