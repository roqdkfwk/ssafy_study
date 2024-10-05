import java.util.*;
class Solution {
    
    int[][] computers;
    boolean[] visit;
    int answer;
    int n;
    
    public int solution(int n, int[][] computers) {
        this.computers = computers;
        this.n = n;
        visit = new boolean[n];
        
        // dfs시작        
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;    // i번째 컴퓨터에 방문하지 않았다면
                dfs(i);             // i번째 컴퓨터부터 dfs 시작
                answer++;            // dfs가 한 번 끝나면 한 개의 네트워크를 모두 탐색한 것
            }
        }
        
        return answer;
    }
    
    void dfs(int start) {
        
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {  // stack이 빌 때까지 탐색
            int curr = stack.pop();
            
            for (int i = 0; i < n; i++) {           // 0번부터 (n - 1)번 컴퓨터까지 돌면서
                if (!visit[i]                       // i번째 컴퓨터에 방문하지 않았고
                    && computers[curr][i] == 1) {   // curr번과 i번 컴퓨터가 연결되어 있는 경우
                    visit[i] = true;
                    stack.add(i);
                }
            }
        }
    }
}


























