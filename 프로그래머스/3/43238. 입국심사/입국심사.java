import java.util.*;

class Solution {
    
    int n, size;
    int[] times;
    
    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;
        this.size = times.length;
        
        long left = 0;
        long right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (calculate(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return right;
    }
    
    private boolean calculate(long MID) {
        long count = 0;  // 심사할 수 있는 사람 수
        for (int i = 0; i < size; i++) {
            count += (MID / times[i]); // MID분이 지났을 때 i번째 심사관이 심사할 수 있는 사람 수를 모두 더함
            if (count >= n) {
                return true;
            }
        }
        
        if (count < n) {
            return false;
        }
        
        return true;
    }
}