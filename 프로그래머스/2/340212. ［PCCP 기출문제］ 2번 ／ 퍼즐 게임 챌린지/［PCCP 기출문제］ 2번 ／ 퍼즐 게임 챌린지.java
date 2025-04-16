import java.util.*;
class Solution {
    
    /**
     * 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값 >> 이분탐색으로 lower bound
     */
    public int solution(int[] diffs, int[] times, long limit) {
        int min = 1;
        int max = 100001;
        
        while (min < max) {
            int mid = (min + max) / 2;
            
            if (requiredTime(diffs, times, mid) > limit) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        
        return min;
    }
    
    private long requiredTime(int[] diffs, int[] times, int level) {
        long result = 0;
        
        if (diffs[0] <= level) {
            result += times[0];
        } else {
            result += times[0] * (diffs[0] - level);
        }
        
        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                result += times[i];
            } else {
                result += (times[i] + times[i - 1]) * (diffs[i] - level);
                result += times[i];
            }
        }
        
        return result;
    }
}