import java.util.*;
class Solution {
    
    Queue<String> queue;
    Stack<String> stack;
    List<String> left;
    boolean answer;
    
    boolean solution(String s) {
        this.queue = new LinkedList<>();
        this.stack = new Stack<>();
        this.left = new ArrayList<>();
        
        left.add("(");
        
        for (int i = 0; i < s.length(); i++)
            queue.add(String.valueOf(s.charAt(i)));
        
        for (String str : queue) {
            if (str.equals("("))
                stack.add(str);
            else {
                if (stack.isEmpty())
                    return false;
                
                stack.pop();
            }
        }

        if (stack.isEmpty()) return true;
        return false;
    }
}