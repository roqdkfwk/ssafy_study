import java.util.*;
class Solution {
    public List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList<>();
        
        s = s.substring(2);
        
        s = s.substring(0, s.length() - 2);
        
        s = s.replace("},{", "-");
        
        String[] arr = s.split("-");
        
        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
            
        for (String str1 : arr) {
            String[] tmp = str1.split(",");
            
            for (String str2 : tmp) {
                int num = Integer.parseInt(str2);
                if (answer.contains(num)) continue;
                
                answer.add(num);
            }
        }
        
        return answer;
    }
}



















