import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int N, M;
    static int[] lastYearRank;  // 지난 해 순위
    static int[] inDegree;      // 앞에 있는 팀의 수
    static List<Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        InputHandler();

        System.out.print(sb);
    }

    private static void Solution() {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        int[] currentInDegree = Arrays.copyOf(inDegree, N + 1);

        for (int i = 1; i <= N; i++) {
            if (currentInDegree[i] == 0) {
                queue.add(i);
            }
        }

        boolean multipleOrders = false;
        for (int i = 0; i < N; i++) {
            if (queue.isEmpty()) {
                sb.append("IMPOSSIBLE").append("\n");
                return;
            }

            if (queue.size() > 1) {
                multipleOrders = true;
            }

            int current = queue.poll();
            result.add(current);

            for (int neighbor : adjList[current]) {
                currentInDegree[neighbor]--;
                if (currentInDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (multipleOrders) {
            sb.append("?\n");
        } else {
            for (int i = 0; i < result.size(); i++) {
                sb.append(result.get(i));
                if (i < result.size() - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            lastYearRank = new int[N + 1];
            inDegree = new int[N + 1];
            adjList = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                lastYearRank[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    adjList[lastYearRank[i]].add(lastYearRank[j]);
                    inDegree[lastYearRank[j]]++;
                }
            }

            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                if (adjList[A].remove((Integer) B)) {
                    adjList[B].add(A);
                    inDegree[A]++;
                    inDegree[B]--;
                } else {
                    adjList[B].remove((Integer) A);
                    adjList[A].add(B);
                    inDegree[B]++;
                    inDegree[A]--;
                }
            }

            Solution();
        }
    }
}