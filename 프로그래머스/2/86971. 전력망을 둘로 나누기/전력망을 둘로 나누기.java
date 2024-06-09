import java.util.*;

class Solution {
    
    static int[][] adj; // 인접행렬
    static int A, B;
    static int answer;  // 정답
    static boolean[] visit; // 방문처리 
    
    public int solution(int n, int[][] wires) {
        
        answer = 987654321;
        
        adj = new int[n + 1][n + 1];
        for (int i = 0; i < wires.length; i++)  // 인접행렬 완성
            adj[wires[i][0]][wires[i][1]] = adj[wires[i][1]][wires[i][0]] = 1;
        
        for (int i = 0; i < wires.length; i++) {
            A = wires[i][0];
            B = wires[i][1];
            
            // 선을 끊고
            adj[A][B] = adj[B][A] = 0;
            
            // A지점부터 탐색을 시작, 시작 지점은 A, B 상관은 없다.
            answer = Math.min(answer, BFS(n, A));
            
            // 탐색을 완료했으면 선을 다시 연결
            adj[A][B] = adj[B][A] = 1;
        }
        
        return answer;
    }   // solution
    
    public int BFS(int N, int start) {   // 송전탑의 개수, 시작 위치
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int cnt = 1;
        
        visit = new boolean[N + 1];
        visit[start] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int i = 1; i <= N; i++) {
                // now와 i 송전탑이 연결되어 있고 && 방문하지 않은 송전탑이라면
                if (adj[now][i] == 1 && !visit[i]) {
                    queue.add(i);
                    cnt++;  // 연결된 송전탑의 개수를 1만큼 증가시킨 후
                    visit[i] = true;    // 방문처리
                }
            }
        }
        
        return Math.abs(N - 2 * cnt);
    }   // BFS
}