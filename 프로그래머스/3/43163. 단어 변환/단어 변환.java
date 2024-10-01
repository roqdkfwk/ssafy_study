import java.util.*;
import java.io.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Queue<String> queue = new LinkedList<>();
        
        if (!set.contains(target)) {    // set 안에 target 단어가 없다면
            return 0;                   // 0을 반환하고 종료
        }
        queue.add(begin);               // queue에 시작 단어를 넣음
        set.remove(begin);              // set에서 시작 단어를 제거
        while (!queue.isEmpty()) {      // bfs 시작
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (curr.equals(target)) {  // target에 도달한 경우 bfs 종료
                    return answer;
                }
                
                Iterator<String> iter = set.iterator();
                while (iter.hasNext()) {            // set를 순회하면서 word를 꺼내옴
                    String word = iter.next();
                    if (canConvert(curr, word)) {   // curr에서 word로 변환할 수 있다면
                        queue.add(word);            // queue에 word를 넣고
                        iter.remove();          // set에서 word를 제거
                    }
                }
            }
            
            answer++;
        }
        
        return answer;
    }
    
    boolean canConvert(String str1, String str2) {
        int cnt = 0;    // 서로 다른 알파벳의 개수
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                cnt++;
            }
        }
        
        return cnt == 1;    // 서로 다른 알파벳의 개수가 1개인지 아닌지 반환
    }
}
