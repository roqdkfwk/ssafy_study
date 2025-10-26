import java.util.*;
class Solution {
    
    int n;              // 1 ~ n 까지의 정수
    int[][] q;          // 각 시도에서 입력한 5개의 숫자
    int[] ans;          // 각 시도에 대한 시스템 응답 (일치 개수)
    int answer = 0;     // 가능한 비밀 코드 조합의 개수

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        this.answer = 0;

        // n개 중 5개를 뽑는 조합을 생성
        combination(1, new ArrayList<>());

        return answer;
    }

    // 1 ~ n까지 숫자 중 5개 조합 생성
    private void combination(int start, List<Integer> selected) {
        if (selected.size() == 5) {
            if (isValid(selected)) answer++;
            return;
        }

        for (int i = start; i <= n; i++) {
            selected.add(i);
            combination(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    // 현재 조합이 시스템 응답(ans)과 일치하는지 검증
    private boolean isValid(List<Integer> secret) {
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;
            for (int num : q[i]) {
                if (secret.contains(num)) {
                    matchCount++;
                }
            }
            
            if (matchCount != ans[i]) {
                return false;
            }
        }
        return true;
    }
}
