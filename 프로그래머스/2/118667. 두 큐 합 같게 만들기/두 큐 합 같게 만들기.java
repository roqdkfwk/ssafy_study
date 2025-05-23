import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum1 = 0;
        long sum2 = 0;
        Deque<Integer> dq1 = new ArrayDeque<>();
        Deque<Integer> dq2 = new ArrayDeque<>();
        for (int num : queue1) {
            sum1 += num;
            dq1.add(num);
        }
        
        for (int num : queue2) {
            sum2 += num;
            dq2.add(num);
        }
        
        if ((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        long target = (sum1 + sum2) / 2;
        if (sum1 == target) {
            return 0;
        }
        
        answer = 0;
        while (sum1 != target) {
            if (sum1 > target) {
                int num = dq1.poll();
                dq2.add(num);
                sum1 -= num;
                sum2 += num;
                answer++;
            } else {
                int num = dq2.poll();
                dq1.add(num);
                sum2 -= num;
                sum1 += num;
                answer++;
            }
            
            if (answer == 3 * queue1.length - 2) {
                return -1;
            }
        }
        
        return answer;
    }
}