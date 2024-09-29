import java.util.*;
import java.io.*;

class Solution {
    
    String[][] tickets;
    int size;
    TreeMap<String, List<String>> map;
    String[] answer;
    boolean flag;
        
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.size = tickets.length;
        answer = new String[size + 1];
        
        map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            String key = tickets[i][0];
            String value = tickets[i][1];
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
        
        flag = false;
        dfs("ICN", 0);
        
        return answer;
    }
    
    void dfs(String key, int depth) {
        if (flag) {
            return;
        }
        
        answer[depth] = key;
        
        if (depth == size) {
            flag = true;
            return;
        }
        
        if (!map.containsKey(key) ||  map.get(key).isEmpty()) {
            return;
        }
        
        List<String> dest = new ArrayList<>(map.get(key));
        Collections.sort(dest);
        
        for (String next : dest) {
            List<String> current = map.get(key);
            current.remove(next);
            dfs(next, depth + 1);
            
            current.add(next);
        }
    }
}