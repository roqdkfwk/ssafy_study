import java.util.*;
import java.io.*;
public class Main {

    // 정점의 집합을 둘로 분할하는데, 같은 집합에 속한 정점끼리는 서로 인접하지 않는 경우 이분 그래프
    // 두 집합을 A, B라고 했을 때
    // A집합의 정점과 인접한 정점은 모두 B집합, B집합의 정점과 인접한 정점은 모두 A집합에 포함되어야 한다.
    // 특정 정점에서 시작해서 bfs를 이용하면 될듯

    // 정점들을 두 개의 집합으로 분리했을 때, 같은 집합 내의 정점 사이에 간선이 존재하는지 확인
    // or 홀수 개의 정점으로 이루어진 사이클이 존재하는 경우 이분 그래프가 될 수 없다.

    static int T;       // 테스트 케이스의 수
    static int V, E;    // V : 정점의 개수, E : 간선의 개수
    static List<Integer>[] vertexs;
    static List<int[]> edges;
    static boolean[] visited;
    static Set<Integer> A, B;
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 1. 정점에 대한 간선의 정보를 모두 입력
            vertexs = new List[V + 1];
            for (int i = 1; i <= V; i++)
                vertexs[i] = new ArrayList<>();

            edges = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                vertexs[A].add(B);
                vertexs[B].add(A);
                edges.add(new int[] {A, B});
            }

            // 2. bfs를 통해서 정점들을 두 개의 집합으로 나눈다.
            A = new HashSet<>();
            B = new HashSet<>();
            visited = new boolean[V + 1];
            for (int i = 1; i <= V; i++) {
                if (checkedVertex(i)) continue;

                bfs(i);
            }

            // 3. 입력했던 간선의 정보를 확인하면서 이어져있는 두 개의 정점이 같은 집합에 포함되어 있는지 확인한다.
            if (isBipartite()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean checkedVertex(int num) {
        return visited[num];
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        visited[start] = true;

        int cycle = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                for (Integer next : vertexs[curr]) {
                    if (visited[next]) continue;

                    visited[next] = true;
                    queue.add(next);
                    if (cycle % 2 == 0) A.add(next);
                    else B.add(next);
                }
            }

            cycle++;
        }
    }

    static boolean isBipartite() {
        for (int[] edge : edges) {
            int V1 = edge[0];
            int V2 = edge[1];

            if ((A.contains(V1) && A.contains(V2)) || (B.contains(V1) && B.contains(V2)))
                return false;
        }

        return true;
    }
}