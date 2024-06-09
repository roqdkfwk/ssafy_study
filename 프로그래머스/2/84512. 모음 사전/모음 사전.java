import java.util.*;
import java.io.*;

// 문자열의 순서가 DFS로 만들어지는 것과 같다.
class Solution {
    
    static String[] words = {"A", "E", "I", "O", "U"};
    static List<String> list;
    static int answer;
    
    public int solution(String word) {
        answer = 0;
        
        list = new ArrayList<>();
    
        for (int i = 0; i < words.length; i++)
            DFS(list, words[i]);
        
        // list를 순회하면서 찾고자 하는 문자열과 같은 문자열을 찾는다면
        // 해당 문자열의 (index + 1)값을 반환
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) {
                answer = i + 1;
                break;
            }
        }
        
        return answer;
    }   // solution
    
    public void DFS(List<String> list, String str) {
        if (str.length() > 5)
            return;
        
        // list가 전달받은 문자열을 포함하지 않는다면 str에 추가
        if (!list.contains(str)) 
            list.add(str);
        
        for (int i = 0; i < words.length; i++)
            DFS(list, str + words[i]);  // str에 다음 문자를 붙여서 DFS
    }   // DFS
}

                























