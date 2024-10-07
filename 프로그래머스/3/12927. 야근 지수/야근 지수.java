import java.util.*;
class Solution {
    
    PriorityQueue<Integer> pq;
    
    public long solution(int n, int[] works) {
        long answer = 0;
        pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
        for (int i = 0; i < n; i++) {
            if (pq.peek() == 0) {
                return 0;
            } else {
                pq.add(pq.poll() - 1);
            }
        }
        for (int i = 0; i < works.length; i++) {
            answer += pq.peek() * pq.poll();
        }
        
        return answer;
    }
}