class Solution {
    
    boolean[] visit;
    
    public int solution(int N, int[][] computers) {
        int answer = 0;
        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {    // i번째 컴퓨터에 방문하지 않은 경우
                answer++;
                DFS(i, computers);
            }
        }
        
        return answer;
    }   // solution
    
    public void DFS(int nodeIndex, int[][] com) {
        visit[nodeIndex] = true;
        
        for (int i = 0; i < com.length; i++) {
            if (!visit[i] && com[nodeIndex][i] == 1) 
                DFS(i, com);
        }
    }   // DFS
}