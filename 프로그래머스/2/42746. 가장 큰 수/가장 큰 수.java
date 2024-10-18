import java.util.*;
class Solution {
    
    TreeMap<Integer, List<String>> map;
    String answer = "";
    
    public String solution(int[] numbers) {
        this.map = new TreeMap<>(Comparator.reverseOrder());
        
        for (int i = 0; i < numbers.length; i++) {
            String str = String.valueOf(numbers[i]);
            int key = (int)(str.charAt(0) - '0');
            
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            List<String> list = map.get(key);
            list.sort((o1, o2) -> {
                return (o2 + o1).compareTo(o1 + o2);
            });
            
            for (String str : list) {
                answer += str;
            }
        }
        
        if (answer.charAt(0) == '0') return "0";
        return answer;
    }
}