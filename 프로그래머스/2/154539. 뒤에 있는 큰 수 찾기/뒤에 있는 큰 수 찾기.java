import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {  // stack에 원소가 있을 때
                if (numbers[i] < stack.peek()) {    // 배열의 숫자보다 크다면
                    answer[i] = stack.peek();   // 해당 값을 answer에 저장
                    break;
                } else {
                    stack.pop();    // 배열의 숫자보다 크지 않다면 pop
                }
            }   // while
            
            stack.push(numbers[i]);
        }   // for
        
        return answer;
    }   // solution
}