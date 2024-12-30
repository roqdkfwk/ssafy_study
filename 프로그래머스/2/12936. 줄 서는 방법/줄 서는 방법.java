import java.util.*;

class Solution {
    public List<Long> solution(int n, long k) {
        List<Long> answer = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        long factorial = 1;
        
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
            if (i < n) {
                factorial *= i;
            }
        }

        // k를 1만큼 빼지 않는 경우
        // n = 5일 때
        // k | 1 | 2 | ... | 23 | 24 | 25 | ... | 47 | 48 | 49 | ...
        // q | 0 | 0 | ... |  0 |  1 |  1 | ... |  1 |  2 |  2 | ...
        // f | 1 | 1 | ... |  1 |  1 |  2 | ... |  2 |  2 |  3 | ...
        // 한 칸씩 어긋나기 때문에 몫(q)가 0이 되는 경우마다 따로 분기처리 해주어야 한다.
        // k를 1만큼 빼서 처리하면 그럴 필요가 없음
        k--;
        
        while (n > 0) {
            int index = (int)(k / factorial);

            answer.add((long)numbers.get(index));
            numbers.remove(index);
            
            k %= factorial;
            n--;
            
            if (n > 0) {
                factorial /= n;
            }
        }
        
        return answer;
    }
}