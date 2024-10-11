import java.util.*;
class Solution {
    
    int[] purse1, purse2;
    long[] dp1, dp2;
    int length;
    
    public long solution(int[] sequence) {
        long answer = 0;
        length = sequence.length;
        purse1 = new int[length];
        purse2 = new int[length];
        dp1 = new long[length];
        dp2 = new long[length];
        
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                purse1[i] = sequence[i];
                purse2[i] = -sequence[i];
            } else {
                purse1[i] = -sequence[i];
                purse2[i] = sequence[i];
            }
        }
        
        if (purse1[0] >= 0) dp1[0] = purse1[0];
        for (int i = 1; i < length; i++) {
            if (dp1[i - 1] + purse1[i] >= 0) {
                dp1[i] = dp1[i - 1] + purse1[i];
            } else {
                dp1[i] = 0;
            }
        }
        // System.out.println(Arrays.toString(dp1));
        
        if (purse2[0] >= 0) dp2[0] = purse2[0];
        for (int i = 1; i < length; i++) {
            if (dp2[i - 1] + purse2[i] >= 0) {
                dp2[i] = dp2[i - 1] + purse2[i];
            } else {
                dp2[i] = 0;
            }
        }
        // System.out.println(Arrays.toString(dp2));
        
        for (int i = 0; i < length; i++) {
            answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
        }
        return answer;
    }
}


























