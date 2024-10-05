import java.util.*;
class Solution {
    
    int[][] scores;
    int size;
    int answer;
    
    public int solution(int[][] scores) {
        this.scores = scores;
        size = scores.length;
        int won0 = scores[0][0];
        int won1 = scores[0][1];
        
        // 근무 태도는 내림차순으로 정렬, 동료 평가는 오름차순으로 정렬
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        
        // for (int i = 0; i < size; i++) {
        //     for (int j = 0; j < 2; j++) {
        //         System.out.print(scores[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        // 근무 태도가 최고 점수인 경우는 고려할 필요가 없으니 i = 1부터 시작
        int max = scores[0][1];
        for (int i = 1; i < size; i++) {
            if (scores[i][1] < max ) {  // 근무 태도와 동료 평가가 모두 낮은 경우
                if (scores[i][0] == won0 && scores[i][1] == won1) { // 완호인 경우
                    return -1;
                }
                
                scores[i][0] = scores[i][1] = 0;    // 인센티브 지급에서 제외하기 위해
            } else {
                max = scores[i][1];
            }
        }
        
        int sum = won0 + won1;
        answer = 1;
        for (int i = 0; i < size; i++) {
            if (scores[i][0] + scores[i][1] > sum) {
                answer++;
            }
        }
        
        return answer;
    }
}