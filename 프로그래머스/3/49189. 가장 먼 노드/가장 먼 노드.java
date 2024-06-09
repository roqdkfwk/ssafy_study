import java.util.*;

class Solution {
    
    static Queue<Integer> queue;
    static int[] dist;   // 거리를 저장할 배열
    static boolean[] visit; // 방문처리할 배열
    static List<Integer>[] list;    // 인접리스트
    static int maxDist = 0;
    static int answer;
    
    public int solution(int N, int[][] edge) {
        answer = 0;
        
        dist = new int[N + 1];
        visit = new boolean[N + 1];
        
        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            list[i] = new ArrayList<>();
        
        for (int i = 0; i < edge.length; i++) {
            int A = edge[i][0];
            int B = edge[i][1];
            
            list[A].add(B);
            list[B].add(A);
        }   // 인접리스트 완성
        
        BFS(1, edge);
        
        Arrays.sort(dist);
        maxDist = dist[N];
        for (int i = N; i > 0; i--) {
            if (dist[i] == maxDist) {
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
    
    public void BFS(int start, int[][] edge) {
        queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int i = 0; i < list[now].size(); i++) {
                if (!visit[list[now].get(i)]) {
                    queue.add(list[now].get(i));
                    visit[list[now].get(i)] = true;
                    dist[list[now].get(i)] = dist[now] + 1;
                }
            }
        }   // while
    }   // BFS
}




















