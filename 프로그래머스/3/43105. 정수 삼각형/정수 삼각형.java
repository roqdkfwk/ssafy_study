class Solution {
    
    int[] dr = {1, 1};
    int[] dc = {0, 1};
    
    public int solution(int[][] triangle) {
        int size = triangle.length;
        int answer = 0;
        // 1. dp배열을 선언하고
        int[][] dp = new int[size][size];
        // 2. 첫 번째 숫자는 triangle과 똑같이 두고
        dp[0][0] = triangle[0][0];
        // 3. 나머지 부분은 위에서부터 내려오면서
        for (int r = 0; r < size - 1; r++) {
            for (int c = 0; c <= r; c++) {
                dp[r + 1][c] = Math.max(
                    dp[r + 1][c], dp[r][c] + triangle[r + 1][c]
                );
                dp[r + 1][c + 1] = Math.max(
                    dp[r + 1][c + 1], dp[r][c] + triangle[r + 1][c + 1]
                );
            }
        }
        
        for (int c = 0; c < size; c++) {
            answer = Math.max(answer, dp[size - 1][c]);
        }
        
        return answer;
    }
}