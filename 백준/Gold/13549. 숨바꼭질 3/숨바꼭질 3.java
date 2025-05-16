import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[100_001];
        boolean[] visited = new boolean[100_001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(N);
        dist[N] = 0;
        visited[N] = true;

        while (!deque.isEmpty()) {
            int curr = deque.poll();

            if (curr == K) {
                System.out.println(dist[K]);
                return;
            }
            
            // 순간 이동은 0초가 걸리므로 최단 경로를 찾기 위해서 앞에 넣는다.
            if (curr * 2 <= 100000 && !visited[curr * 2]) {
                dist[curr * 2] = dist[curr];
                visited[curr * 2] = true;
                deque.addFirst(curr * 2);
            }

            if (curr - 1 >= 0 && !visited[curr - 1]) {
                dist[curr - 1] = dist[curr] + 1;
                visited[curr - 1] = true;
                deque.addLast(curr - 1);
            }

            if (curr + 1 <= 100000 && !visited[curr + 1]) {
                dist[curr + 1] = dist[curr] + 1;
                visited[curr + 1] = true;
                deque.addLast(curr + 1);
            }
        }
    }
}