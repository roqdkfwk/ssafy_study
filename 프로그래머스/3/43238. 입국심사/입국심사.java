class Solution {
    public long solution(int n, int[] times) {
        // 1. left는 0, answer는 Max
        long left = 0, answer = Long.MAX_VALUE;
        
        // 2. 이분탐색으로 특정 시간에 심사관별로 심사할 수 있는 인원의 총합을 구함
        while (left < answer) {
            long mid = left + (answer - left) / 2;
            long count = 0;  // 입국 심사를 받을 수 있는 사람들의 합
            
            for (int i = 0; i < times.length; i++) {
                count += mid / times[i];
                if (count >= n) {
                    break;
                }
            }
            
            if (count >= n) {
                answer  = mid;
            } else {
                left = mid + 1;
            }
        }
        // 3. 구한 값 중 가장 작은 값을 찾음
        return answer;
    }
}