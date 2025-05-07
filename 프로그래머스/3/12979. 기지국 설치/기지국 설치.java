import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = 2 * w + 1; // 한 기지국이 커버할 수 있는 범위

        // 첫 번째 기지국 이전의 범위를 계산
        // start 이전의 범위까지는 커버가 되었음
        int start = 1;
        for (int station : stations) {
            int left = Math.max(station - w, 1);
            if (start < left) {
                // 기지국이 닿지 않는 구간에 필요한 추가 기지국 계산
                int uncovered = left - start;
                answer += uncovered / range + (uncovered % range == 0 ? 0 : 1);
            }
            // 다음 시작 범위 갱신
            start = Math.min(station + w + 1, n + 1);
        }

        // 마지막 기지국 이후의 범위를 계산
        if (start <= n) {
            int uncovered = n - start + 1;
            answer += uncovered / range + (uncovered % range == 0 ? 0 : 1);
        }

        return answer;
    }
}
