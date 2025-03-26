import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty() || prices[stack.peek()] <= prices[i]) {
                stack.add(i);
                continue;
            }
            
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.add(i);
        }
        
        while (!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = answer.length - 1 - index;
        }
        
        return answer;
    }
}