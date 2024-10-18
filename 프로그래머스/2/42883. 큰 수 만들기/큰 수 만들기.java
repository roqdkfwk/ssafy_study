import java.util.*;
class Solution {
    
    char[] ch;
    Stack<Character> stack;
    
    public String solution(String number, int k) {
        String answer = "";
        ch = answer.toCharArray();
        stack = new Stack<>();
        
        int count = 0;
        stack.add(number.charAt(0));
        for (int i = 1; i < number.length(); i++) {
            if (!stack.isEmpty() && number.charAt(i) <= stack.peek()) {
                stack.add(number.charAt(i));
            } else {
                while (!stack.isEmpty() && number.charAt(i) > stack.peek()) {
                    if (count == k) {
                        break;
                    }
                    stack.pop();
                    count++;
                }
                stack.add(number.charAt(i));
            }
        }
        
        while (count < k ) {
            stack.pop();
            count++;
        }
        
        for (Character ch : stack) {
            answer += String.valueOf(ch);
        }
        
        return answer;
    }
}























