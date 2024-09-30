import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;       
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while (pq.peek() < K) {
            int A = pq.poll();
            if (pq.isEmpty())
                return -1;
            
            int B = pq.poll() * 2;
            pq.add(A + B);
            
            answer++;
        }
        
        return answer;
    }
}