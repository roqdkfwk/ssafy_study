import java.util.*;
import java.io.*;
public class Main {

    static class State{
        int r, c, key, moveCount;

        public State() {}
        public State(int r, int c, int key, int moveCount) {
            this.r = r;
            this.c = c;
            this.key = key;
            this.moveCount = moveCount;
        }
    }

    static int R, C;
    static char[][] maze;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][][] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        
        maze = new char[R][C];                  // 미로
        for (int r = 0; r < R; r++) {
            maze[r] = br.readLine().toCharArray();
        }

        visited = new boolean[R][C][1 << 6];
    }

    private static void solution() {
        Deque<State> queue = new ArrayDeque<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maze[r][c] != '0') continue;
                queue.add(new State(r, c, 0, 0));
                visited[r][c][0] = true;
                break;
            }
        }

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (maze[curr.r][curr.c] == '1') {
                answer = curr.moveCount;
                return;
            }

            // 현재 가지고 있는 열쇠
            int key = curr.key;

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                if (!isValid(nr, nc)) continue;

                char ch = maze[nr][nc];
                State next = new State(nr, nc, key, curr.moveCount + 1);

                if ('A' <= ch && ch <= 'F') {
                    if ((key & (1 << (ch - 'A'))) == 0) continue;
                } else if ('a' <= ch && ch <= 'f') {
                    next.key = key | (1 << (ch - 'a'));
                }

                if (visited[nr][nc][key]) continue;

                visited[nr][nc][key] = true;
                queue.add(next);
            }
        }

        // 탈출이 불가능한 경우
        answer = -1;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C && maze[r][c] != '#';
    }

    private static void printResult() {
        System.out.println(answer);
    }
}