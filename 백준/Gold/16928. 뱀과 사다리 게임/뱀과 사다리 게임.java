import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
    static int answer;
    static boolean[] visited;
    static HashMap<Integer, Integer> hashmap;
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        hashmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < N + M; i++) {
        	st = new StringTokenizer(br.readLine());
        	hashmap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        visited = new boolean[101];
        bfs();
        
        System.out.print(answer);
    }

    // bfs(시작 지점, 움직인 횟수
    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= 6; i++) queue.add(new int[] {1, i});
        visited[1] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            answer++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int position = curr[0] + curr[1];

                if (position == 100) return;
                if (position > 100) continue;
                if (visited[position]) continue;

                visited[position] = true;

                if (hashmap.containsKey(position)) {
                    for (int j = 1; j <= 6; j++) queue.add(new int[] {hashmap.get(position), j});
                } else {
                    for (int j = 1; j <= 6; j++) queue.add(new int[] {position, j});
                }
            }
        }
    }
}