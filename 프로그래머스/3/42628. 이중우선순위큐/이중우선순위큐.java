import java.util.*;

class Solution {
    
    Deque<Integer> dq;
    String[] operations;
    
    public int[] solution(String[] operations) {
        this.operations = operations;
        int[] answer = {0, 0};
        int size = operations.length;
        dq = new ArrayDeque<>();
        
        for (int i = 0; i < size; i++) {
            String oper = operations[i].split(" ")[0];
            String num = operations[i].split(" ")[1];
            
            if (oper.equals("I")) {
                dq.addFirst(Integer.parseInt(num));
                List<Integer> list = new ArrayList<>(dq);
                Collections.sort(list);
                
                dq.clear();
                dq.addAll(list);
            } else {
                if (dq.size() == 0) continue;
                
                if (num.equals("1")) {
                    dq.pollLast();
                } else {
                    dq.pollFirst();
                }
            }
        }
        
        if (dq.isEmpty()) {
            return answer;
        }
        
        answer[0] = dq.getLast();
        answer[1] = dq.getFirst();
        return answer;
    }
}