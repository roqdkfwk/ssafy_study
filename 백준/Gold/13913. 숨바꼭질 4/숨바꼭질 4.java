import java.util.*;
import java.io.*;

public class Main {

    static int[] prev = new int[100_001];
    static int[] dist = new int[100_001];
    static boolean[] visited = new boolean[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bfs(N, K);
    }

    private static void bfs(int start, int end) {
        prev[start] = -1;
        dist[start] = 0;
        visited[start] = true;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == end) {
                // 거리 출력
                System.out.println(dist[end]);

                Deque<Integer> stack = new ArrayDeque<>();
                for (int root = end; root != -1; root = prev[root]) {
                    stack.push(root);
                }

                // 경로 출력
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop() + " ");
                }
                return;
            }

            for (int next : new int[] {curr -1, curr + 1, 2 * curr}) {
                if (next < 0 || next > 100_000 || visited[next]) continue;

                prev[next] = curr;
                dist[next] = dist[curr] + 1;
                visited[next] = true;
                queue.add(next);
            }
        }
    }
}