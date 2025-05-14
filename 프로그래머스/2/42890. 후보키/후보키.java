import java.util.*;
class Solution {
    
    // 유일성을 먼저 만족시킨 후
    // 최소성을 만족시켜야 한다.
    
    public int solution(String[][] relation) {
        int tuples = relation.length;
        int columns = relation[0].length;
        
        // 유일성을 만족시키는 조합 찾음
        List<Integer> uniqueKey = new ArrayList<>();
        for (int i = 1; i < (1 << columns); i++) {
            if (isUnique(i, relation, tuples, columns)) {
                uniqueKey.add(i);
            }
        }
        
        // 최소성을 만족시키는 조합의 개수 찾음
        return findMinKey(uniqueKey);
    }
    
    boolean isUnique(int key, String[][] relation, int row, int col) {
        // key로 만들 수 있는 조합을 저장할 Set
        Set<String> keySet = new HashSet<>();
        for (int r = 0; r < row; r++) {
            String keyStr = "";
            for (int i = 0; i < col; i++) {
                if ((key & (1 << i)) == 0) continue;
                
                keyStr += relation[r][i];
            }
            
            // 이미 집합에 있는 조합이라면 후보키가 될 수 없으므로 false를 반환
            if (keySet.contains(keyStr)) {
                return false;
            }
            
            keySet.add(keyStr);
        }
        // 위의 모든 과정을 통과한다면 true를 반환
        return true;
    }
    
    int findMinKey(List<Integer> uniqueKey) {
        int size = uniqueKey.size();
        Set<Integer> resultSet = new HashSet<>(uniqueKey);
        
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int A = uniqueKey.get(i);
                int B = uniqueKey.get(j);
                
                if ((A & B) == A) resultSet.remove(B);
            }
        }
        
        return resultSet.size();
    }
}

























