import java.util.*;
import java.io.*;
public class Main {

    static int R, C;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<HashSet<String>> emptySpace;
    static int[][] answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 맵 입력
        grid = new int[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                grid[r][c] = str.charAt(c) - '0';
            }
        }

        answer = new int[R][C];
    }

    private static void solution() {
        emptySpace = new ArrayList<>();

        // 연결되어 있는 빈 공간들의 위치와 개수를 잦음
        findEmptySpace(grid);

        // 정답 맵을 만듦
//        return makeAnswerGird(grid);
    }

    private static void findEmptySpace(int[][] grid) {
        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1 || visited[r][c]) continue;

                // 빈 공간을 기준으로 dfs를 수행해서 칸의 개수와 위치를 찾음
                visited[r][c] = true;
                dfs(grid, r, c, visited);
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) answer[r][c]++;
                answer[r][c] %= 10;
            }
        }
    }

    private static void dfs(int[][] grid, int r, int c, boolean[][] visited) {
        Deque<int[]> dq = new ArrayDeque<>();
        HashSet<String> answerSet = new HashSet<>();
        HashSet<String> set = new HashSet<>();
        dq.add(new int[] {r, c});

        while (!dq.isEmpty()) {
            int[] curr = dq.poll();
            set.add(curr[0] + "," + curr[1]);

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (!isvalid(nr, nc) || visited[nr][nc]) continue;
                if (grid[nr][nc] == 1) {
                    answerSet.add(nr + "," + nc);
                    continue;
                }

                visited[nr][nc] = true;
                dq.add(new int[] {nr, nc});
            }
        }

        for (String str : answerSet) {
            String[] location = str.split(",");
            answer[Integer.parseInt(location[0])][Integer.parseInt(location[1])] += set.size();
        }
    }

    private static boolean isvalid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void printResult(int[][] answer) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(answer[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}