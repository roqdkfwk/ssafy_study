import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int TO, DISTANCE;

        public Node(int TO, int DISTANCE) {
            this.TO = TO;
            this.DISTANCE = DISTANCE;
        }

        @Override
        public int compareTo(Node n) {
            return this.DISTANCE - n.DISTANCE;
        }
    }

    static int N, M;    // 장소의 수, 도로의 수
    static int S, D;    // 시작점, 도착점
    static List<Node>[] nodes;
    static int[] distance;
    static List<Integer>[] shortestPath;
    static boolean[][] isShortest;  //
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0) break;    // 0 0을 입력하면 종료

            nodes = new ArrayList[N];
            shortestPath = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                nodes[i] = new ArrayList<>();
                shortestPath[i] = new ArrayList<>();
            }
            isShortest = new boolean[N][N];

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());   // 시작점
            D = Integer.parseInt(st.nextToken());   // 도착점

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                nodes[Integer.parseInt(st.nextToken())]
                        .add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            int firstShortest = dijkstra(S, false); // 최단 경로를 찾는 첫 번째 다익스트라
            markPath();                                     // 최단 경로에 포함되어 있는 도로를 찾는 markPath
            int secondShortest = dijkstra(S, true); // 두 번째 최단 경로를 찾는 두 번째 다익스트라

            sb.append(secondShortest).append("\n");
        }

        System.out.print(sb);
    }

    private static int dijkstra(int START, boolean isSecond) {
        distance = new int[N];
        Arrays.fill(distance, INF);
        distance[START] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.TO == D) return curr.DISTANCE;
            if (distance[curr.TO] < curr.DISTANCE) continue;

            for (Node next : nodes[curr.TO]) {
                // 두 번째 다익스트라 진행중이고 현재 도로가 최단 경로에 포함된 경우 건너뜀
                if (isSecond && isShortest[curr.TO][next.TO]) continue;

                if (distance[next.TO] > curr.DISTANCE + next.DISTANCE) {
                    distance[next.TO] = curr.DISTANCE + next.DISTANCE;
                    pq.offer(new Node(next.TO, curr.DISTANCE + next.DISTANCE));

                    if (!isSecond) {                                                            // 첫 번째 다익스트라일 때
                        shortestPath[next.TO].clear();                                          // 이전까지 저장된 정보를 지우고
                        shortestPath[next.TO].add(curr.TO);                                     // 더 짧은 도로를 최단 경로에 추가
                    }
                } else if (!isSecond && distance[next.TO] == curr.DISTANCE + next.DISTANCE) {   // 최단 경로와 동일한 경우
                    shortestPath[next.TO].add(curr.TO);                                         // 해당 도로를 최단 경로에 추가
                }
            }
        }

        return distance[D] == INF ? -1 : distance[D];
    }   // dijkstra

    // 도착 지점까지의 최단 경로에 포함되어 있는 모든 도로에 마킹
    private static void markPath() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(D);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int prev : shortestPath[curr]) {
                if (isShortest[prev][curr]) continue;
                isShortest[prev][curr] = true;
                queue.offer(prev);
            }
        }
    }   // markPath
}