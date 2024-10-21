import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return b[0] - a[0];
            }
            return a[col - 1] - b[col - 1];
        });
            
        List<Integer> s = new ArrayList<>();
        for (int r = row_begin - 1; r < row_end; r++) {
            int sum = 0;
            for (int c = 0; c < data[0].length; c++) {
                sum += (data[r][c] % (r + 1));
            }
            s.add(sum);
        }
        
        int answer = s.get(0);
        for (int i = 1; i < s.size(); i++) {
            answer = answer ^ s.get(i);
        }
        return answer;
    }
}


















