import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            String key = phone_book[i];
            map.put(key, 1);
        }
        for (int i = 0; i < phone_book.length; i++) {
            String key = phone_book[i];
            for (int j = 1; j < key.length(); j++) {
                if (map.containsKey(key.substring(0, j))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}