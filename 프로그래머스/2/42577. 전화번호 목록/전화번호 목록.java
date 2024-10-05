import java.util.*;
class Solution {
    
    class Node {
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
        
        public Node () {
            this.isEnd = false;
        }
    }
    
    public boolean solution(String[] phone_book) {
        Node root = new Node();
        for (String str : phone_book) {
            Node node = root;
            
            for (int j = 0; j < str.length(); j++) {
                char key = str.charAt(j);
                
                if (node.isEnd) {  // 현재 노드가 이미 어떤 번호의 끝이라면
                    return false;  // 현재 삽입 중인 번호는 기존 번호의 접두어
                }
                
                node.child.putIfAbsent(key, new Node());
                node = node.child.get(key);
                
                if (j == str.length() - 1) {        // 현재 삽입 중인 번호의 마지막 문자
                    if (!node.child.isEmpty()) {    // 이미 다른 번호가 현재 번호로 시작한다면
                        return false;               // 현재 번호는 다른 번호의 접두어
                    }
                    node.isEnd = true;              // 현재 번호의 끝을 표시
                }
            }
        }
        
        return true;
    }
}