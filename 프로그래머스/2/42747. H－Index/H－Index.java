import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int size = citations.length;
        Arrays.sort(citations);
        
        for (int i = 0; i < size; i++) {
            int h = size - i;
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        // for (int i = size - 1; i >= 0; i--) {
        //     int h = size - i;   // 인용된 개수
        //     System.out.println(h + " " + citations[i]);
        //     if (citations[i] <= h) {
        //         answer = citations[i];
        //         break;
        //     }
        // }
        
        return answer;
    }
}