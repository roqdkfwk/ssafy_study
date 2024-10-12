import java.util.*;
class Solution {
    
    double[] answer;
    List<Long> height;
    
    public double[] solution(int k, int[][] ranges) {
        this.answer = new double[ranges.length];
        this.height = new ArrayList<>();
        
        while (true) {
            height.add((long)k);
            if (k == 1) break;
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = (3 * k) + 1;
            }
        }
        
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = height.size() + ranges[i][1] - 1;

           if (start <= end) {
                for (int j = start; j < end; j++) {
                    answer[i] += (height.get(j) + height.get(j + 1)) / 2.0;
                }
            } else {
                answer[i] = -1;
            }
        }
        
        return answer;
    }
}




























