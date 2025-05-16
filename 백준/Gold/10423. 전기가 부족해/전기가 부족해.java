import java.util.*;
import java.io.*;
public class Main {

    static class Edge implements Comparable<Edge> {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.w, e.w);
        }
    }

    static int N, M, K;
    static Set<Integer> 발전소;
    static int[] parent;
    static List<Edge>[] edges;
    static PriorityQueue<Edge> pq;
    static int cost;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시
        M = Integer.parseInt(st.nextToken());   // 케이블
        K = Integer.parseInt(st.nextToken());   // 발전소

        st = new StringTokenizer(br.readLine());
        발전소 = new HashSet<>();
        for (int i = 0; i < K; i++) {
            발전소.add(Integer.parseInt(st.nextToken()));
        }

        // 집단의 부모를 자기 자신으로 설정
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            Edge e1 = new Edge(A, B, W);
            Edge e2 = new Edge(B, A, W);
            edges[A].add(e1);
            edges[B].add(e2);

            // MST를 발전소에서 시작해야 하므로 발전소를 포함하는 도시가 있는 경우 pq에 저장
            if (발전소.contains(A)) pq.add(e1);
            if (발전소.contains(B)) pq.add(e2);
        }
    }

    /**
     * 케이블이 연결되어 있는 도시에는 발전소가 반드시 하나
     * 서로소 집합
     * 케이블을 설치할 때
     *      1. 사이클을 생성하는지 확인
     *      2. 이미 발전소가 설치되어 있는 도시인지 확인
     */
    private static void solution() {
        // 설치한 케이블의 개수
        // 발전소가 K개인 경우 (N - K)개의 케이블만 설치하면 된다.
        int cable = 0;
        while (cable < N - K) {
            Edge edge = pq.poll();
            int curr = edge.from;
            int next = edge.to;

            // 다음 도시에 이미 발전소가 설치되어 있는 경우
            if (발전소.contains(findParent(next))) continue;

            // 다음 도시에 아직 발전소가 설치되어 있지 않은 경우
            for (Edge e : edges[next]) {
                if (발전소.contains(findParent(e.to))) continue;
                pq.add(e);
            }
            union(curr, next);
            cost += edge.w;
            cable++;
        }
    }

    private static int findParent(int num) {
        if (num != parent[num]) {
            parent[num] = findParent(parent[num]);
        }
        return parent[num];
    }

    private static void union(int from, int to) {
        parent[to] = findParent(from);
    }

    private static void printResult() {
        System.out.println(cost);
    }
}