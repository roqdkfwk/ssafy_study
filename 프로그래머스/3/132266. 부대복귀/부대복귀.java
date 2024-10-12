import java.util.*;
class Solution {
    
    class Node implements Comparable<Node> {
        int To, Time;
        
        public Node (int To, int Time) {
            this.To = To;
            this.Time = Time;
        }
        
        @Override
        public int compareTo(Node N) {
            return this.Time - N.Time;
        }
    }
    
    int n;
    List<Node>[] nodes;
    int[] distance;
    boolean[] visit;
    int[] answer;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        this.n = n;
        this.distance = new int[n + 1];
        
        // roads 입력
        this.nodes = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            this.nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < roads.length; i++) {
            int A = roads[i][0];
            int B = roads[i][1];
            
            this.nodes[A].add(new Node(B, 1));
            this.nodes[B].add(new Node(A, 1));
        }
        
        dijkstra(destination);  // 도착 지점에서 역으로 다익스트라
        
        answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = distance[sources[i]] == 987654321 ? -1 : distance[sources[i]];
        }
        
        return answer;
    }
    
    void dijkstra(int start) {
        distance = new int[n + 1];  // 거리 배열 초기화
        Arrays.fill(distance, 987654321);
        distance[start] = 0;        // 시작 지점까지의 거리는 0
            
        visit = new boolean[n + 1]; // 방문 배열 초기화
        visit[start] = true;        // 시작 지점은 방문처리
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            
            if (distance[curr.To] < curr.Time) continue;
            
            for (Node node : nodes[curr.To]) {
                if (distance[node.To] > distance[curr.To] + 1) {
                    distance[node.To] = distance[curr.To] + 1;
                    queue.add(new Node(node.To, distance[curr.To] + 1));
                }
            }
        }
    }
}



























