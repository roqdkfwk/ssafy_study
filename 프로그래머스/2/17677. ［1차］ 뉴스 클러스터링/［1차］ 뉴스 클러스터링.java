import java.util.*;
class Solution {
    
    Map<String, Integer> map1;
    Map<String, Integer> map2;
    int len1, len2, size1, size2;
    char[] ch1, ch2;
    Set<String> set1, set2;
    int com, sum;
    
    public int solution(String str1, String str2) {
        len1 = str1.length();
        len2 = str2.length();
        ch1 = new char[len1];
        ch2 = new char[len2];
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        ch1 = str1.toCharArray();
        ch2 = str2.toCharArray();
        
        map1 = new HashMap<>();
        map2 = new HashMap<>();        
        for (int i = 0; i < len1 - 1; i++) {
            if (check(i, i + 1, ch1)) {
                String key = String.valueOf(ch1[i]) + String.valueOf(ch1[i + 1]);
                System.out.println("map1 : " + key);
                map1.put(key, map1.getOrDefault(key, 0) + 1);
            }
        }
        System.out.println("================");
        for (int i = 0; i < len2 - 1; i++) {
            if (check(i, i + 1, ch2)) {
                String key = String.valueOf(ch2[i]) + String.valueOf(ch2[i + 1]);
                System.out.println("map2 : " + key);
                map2.put(key, map2.getOrDefault(key, 0) + 1);
            }
        }
        
        set1 = new HashSet<>();
        set2 = new HashSet<>();
        set1 = map1.keySet();
        set2 = map2.keySet();
        
        Iterator<String> iter1 = set1.iterator();
        Iterator<String> iter2 = set2.iterator();
        while (iter1.hasNext()) {
            String key = iter1.next();
            size1 += map1.get(key);
        }
        while (iter2.hasNext()) {
            String key = iter2.next();
            size2 += map2.get(key);
        }
        
        // System.out.print(size1 + " " + size2);
        
        int cnt = 0;
        iter1 = set1.iterator();
        while (iter1.hasNext()) {
            String key = iter1.next();
            if (map2.containsKey(key)) {
                sum += Math.max(map1.get(key), map2.get(key));
                com += Math.min(map1.get(key), map2.get(key));
            } else {
                sum += map1.get(key);
            }
        }
        iter2 = set2.iterator();
        while (iter2.hasNext()) {
            String key = iter2.next();
            if (!map1.containsKey(key)) {
                sum += map2.get(key);
            }
        }
        
        // System.out.println("com : " + com);
        // System.out.println("sum : " + sum);

        if (sum == 0) {
            return 65536;
        } else {
            double j = ((double)com / sum) * 65536;
            // System.out.println("double : " + j);
            int answer = (int) j;

            return answer;            
        }
    }
    
    boolean check(int idx1, int idx2, char[] ch) {
        // a = 97, z = 122
        if (ch[idx1] < 97 || ch[idx1] > 122 || ch[idx2] < 97 || ch[idx2] > 122)
            return false;
        return true;
    }
}


























