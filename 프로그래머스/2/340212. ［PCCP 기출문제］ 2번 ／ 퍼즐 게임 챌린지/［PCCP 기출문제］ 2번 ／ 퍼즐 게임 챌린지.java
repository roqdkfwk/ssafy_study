import java.util.*;
class Solution {
    // 숙련도는 1~100,000
    // 숙련도의 범위가 넓으니 이분탐색
    int[] diffs;
    int[] times;
    long limit;
    int answer = Integer.MAX_VALUE;
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        int left = 1;
        int right = 100000;
        
        while (left < right) {
            // mid : 숙련도
            int mid = (left + right) / 2;
            
            // 숙련도가 mid일 때
            if (limit >= calculateTime(mid)) {  // 제한 시간 안에 퍼즐을 해결할 수 있는 경우
                right = mid;
            } else {                            // 제한 시간 안에 퍼즐을 해결할 수 없는 경우
                left = mid + 1;
            }
        }
        return right;
    }
    
    // 숙련도가 level일 때, 소요 시간을 계산
    long calculateTime(int level) {
        long time = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (level >= diffs[i]) {    // 숙련도가 더 높은 경우
                time += times[i];
            } else {                                        // 숙련도가 더 낮은 경우
                if (i > 0) {
                    int count = diffs[i] - level;               // 틀리는 횟수
                    time += count * (times[i] + times[i - 1]);  // 틀리는 횟수 * 소요 시간
                    time += times[i];
                } else {
                    int count = diffs[i] - level;               // 틀리는 횟수
                    time += count * times[i];
                    time += times[i];
                }
            }
        }
        
        // 숙련도가 level일 때, 퍼즐을 푸는데 걸리는 소요 시간은 time
        return time;
    }
}