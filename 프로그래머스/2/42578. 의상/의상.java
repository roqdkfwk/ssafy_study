import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String name = clothes[i][0];
            String category = clothes[i][1];
            
            map.put(category, map.getOrDefault(category, new ArrayList<>()));
            map.get(category).add(name);
        }
        
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            answer *= (map.get(key).size() + 1);
        }
        
        return answer - 1;
    }
}