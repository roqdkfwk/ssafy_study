import java.util.*;
class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        int size = speeds.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        int max = 0;
        
        // 앞선 기능부터 배포 날짜를 구한다.
        for (int i = 0; i < size; i++) {
            int day = 0;
            if ((100 - progresses[i]) % speeds[i] == 0) {
                day = (100 - progresses[i]) / speeds[i];
            } else {
                day = ((100 - progresses[i]) / speeds[i]) + 1;
            }
            
            // 앞선 기능의 배포 날짜와 현재 개발중인 기능의 배포 날짜 중 늦은 날짜에 1만큼 더한다.(Map형태)
            max = Math.max(max, day);   // i번째 기능이 배포되는 날
            // Map을 순회하면서 answer에 저장
            map.put(max, map.getOrDefault(max, 0) + 1);
        }
        
        Set<Integer> keySet = map.keySet();
        List<Integer> days = new ArrayList(keySet);
        days.sort(null);
        for (int day : days) {
            answer.add(map.get(day));
        }
        
        return answer;
    }
}