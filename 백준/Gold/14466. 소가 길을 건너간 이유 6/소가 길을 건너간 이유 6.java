import java.util.*;
import java.io.*;
public class Main {

    static int N, K, R;
    static int[][] farm;
    static Set<String> roads;
    static Set<String> cows;
    static List<Integer> cowGroups;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void solution() {
        cowGroups = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;

                visited[r][c] = true;
                bfs(r, c);
            }
        }

        countSeperatedCows();
    }

    private static void countSeperatedCows() {
        int size = cowGroups.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                answer += (cowGroups.get(i) * cowGroups.get(j));
            }
        }
    }

    private static void bfs(int r, int c) {
        int cowCount = 0;   // 탐색중인 그룹에 포함되어 있는 소
        if (cows.contains(r + "," + c)) cowCount++;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                // 범위를 벗어나거나, 이미 방문했던 위치인 경우
                // 또는 길이 놓인 경우는 제외
                if (!canMove(nr, nc)) continue;
                if (roads.contains(curr[0] + "," + curr[1] + "," + nr + "," + nc)
                        || roads.contains(nr + "," + nc + "," + curr[0] + "," + curr[1])) continue;

                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});

                if (cows.contains(nr + "," + nc)) cowCount++;
            }
        }
        cowGroups.add(cowCount);
    }

    private static boolean canMove(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N && !visited[r][c];
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        farm = new int[N][N];
        visited = new boolean[N][N];
        roads = new HashSet<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            roads.add(
                    (Integer.parseInt(st.nextToken()) - 1) + "," + (Integer.parseInt(st.nextToken()) - 1)
                    + "," + (Integer.parseInt(st.nextToken()) - 1) + "," + (Integer.parseInt(st.nextToken()) - 1)
            );
        }

        cows = new HashSet<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cows.add((Integer.parseInt(st.nextToken()) - 1) + "," + (Integer.parseInt(st.nextToken()) - 1));
        }
    }
}