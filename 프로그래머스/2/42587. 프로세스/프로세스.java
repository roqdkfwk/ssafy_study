import java.util.*;
class Solution {
    
    class Process {
        int Priority;
        char Ch;
        
        public Process(int Priority, char Ch) {
            this.Priority = Priority;
            this.Ch = Ch;
        }
    }
    
    Deque<Process> dq;
    TreeMap<Integer, Integer> map;  // 프로세스들의 우선 순위와 프로세스의 개수를 저장하는 map
    int answer;
    
    public int solution(int[] priorities, int location) {
        dq = new ArrayDeque<>();
        map = new TreeMap<>();
        answer = 0;
        
        // 우선 순위를 덱에 넣는다.
        // 넣으면서 map도 초기화
        for (int i = 0; i < priorities.length; i++) {
            int num = priorities[i];
            dq.add(new Process(num, (char)('A' + i)));
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        char CH = (char)(location + 'A');
        
        while (true) {
            int pri = dq.peekFirst().Priority;  // 제일 앞에 위치한 프로세스의 우선 순위
            
            if (map.higherKey(pri) == null) {   // 제일 앞에 위치한 프로세스의 우선 순위가 가장 큰 경우
                answer++;
                Process curr = dq.pollFirst();
                if (curr.Ch == CH) {
                    return answer;
                }
                
                if (map.get(pri) == 1) {
                    map.remove(pri);
                } else {
                    map.put(pri, map.get(pri) - 1);
                }
            } else {    // 제일 앞에 위치한 프로세스보다 우선 순위가 큰 프로세스가 있는 경우
                dq.addLast(dq.pollFirst());
            }
        }  
    }
}