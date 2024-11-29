import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        int index = -1;
        
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        
        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];
            
            if (index < start) {
                answer++;
                index = end - 1;
            }
        }
        
        return answer;
    }
}








