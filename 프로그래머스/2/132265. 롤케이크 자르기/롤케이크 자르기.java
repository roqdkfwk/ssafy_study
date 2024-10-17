import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        int answer = 0;

        // 오른쪽 맵에 모든 토핑을 추가
        for (int t : topping) {
            right.put(t, right.getOrDefault(t, 0) + 1);
        }

        // 왼쪽부터 하나씩 이동하면서 확인
        for (int t : topping) {
            // 왼쪽에 토핑 추가
            left.put(t, left.getOrDefault(t, 0) + 1);

            // 오른쪽에서 토핑 제거
            right.put(t, right.get(t) - 1);
            if (right.get(t) == 0) {
                right.remove(t);
            }

            // 양쪽의 고유한 토핑 수가 같으면 answer 증가
            if (left.size() == right.size()) {
                answer++;
            }
        }

        return answer;
    }
}