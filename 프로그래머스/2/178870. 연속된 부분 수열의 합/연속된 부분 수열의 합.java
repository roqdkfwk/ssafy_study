class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[]{0, sequence.length - 1};
        
        int L = 0;
        int R = 1;
        int sum = sequence[0];
        
        while (L < R) {
            if (sum == k) {
                if (R - L - 1 < answer[1] - answer[0]) {
                    answer[0] = L;
                    answer[1] = R - 1;
                }
                
                sum -= sequence[L++];
                
            } else if (sum > k) {
                sum -= sequence[L++];
                
            } else if (R < sequence.length) {
                sum += sequence[R++];
                
            } else {
                break;
            }
            
        }   // while
        
        return answer;
    }
}