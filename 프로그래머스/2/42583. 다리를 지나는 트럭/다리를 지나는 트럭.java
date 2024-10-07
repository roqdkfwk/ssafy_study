import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // 대기중인 트럭들을 큐에 담는다.
        Queue<Integer> truck = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            truck.add(truck_weights[i]);
        }
        
        // 다리의 길이와 동일한 덱 선언
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; i++) {
            dq.addLast(0);
        }
        
        // 다리 위 트럭들의 무게의 합
        int bw = 0;
        
        while (bw != 0 || !truck.isEmpty()) {
            // 제일 앞에 있는 트럭이 다리 위에서 내리고 무게만큼 감소
            bw -= dq.pollFirst();
            
            // 현재 무게 + 대기중인 트럭의 무게가 중량 제한보다 작거나 같은 경우 트럭을 추가
            if (bw + truck.peek() <= weight) {
                bw += truck.peek();
                dq.addLast(truck.poll());
            // 현재 무게 + 대기중인 트럭의 무게가 중량 제한보다 큰 경우 트럭 추가 X
            } else {
                dq.addLast(0);
            }
                       
            // 한 사이클이 돌고나면 시간을 1만큼 증가
            answer++;
            
            // 만약 모든 트럭이 다리 위에 올라갔다면 바로 종료
            if (truck.isEmpty()) {
                answer += bridge_length;
                return answer;
            }
        }
        
        return answer;
    }
}