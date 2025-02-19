import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] icesGrid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void Solution() {
        int count = 0;
        int time = 0;
        do {
            time++;
            // 1. 빙산을 녹이고
            meltIce();

            // 2. 빙산의 개수를 센다.
            count = countGroup();
        } while (count == 1);

        if (count == 0) {
            answer = 0;
        } else {
            answer = time;
        }
    }

    private static int countGroup() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (icesGrid[r][c] != 0 && !visited[r][c]) {
                    count++;
                    queue.add(new int[] {r, c});
                    visited[r][c] = true;

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int nr = curr[0] + dr[i];
                            int nc = curr[1] + dc[i];

                            if (!isInside(nr, nc) || visited[nr][nc] || icesGrid[nr][nc] == 0) continue;

                            queue.add(new int[] {nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
        }

        return count;
    }

    private static void meltIce() {
        int[][] copiedGrid = new int[R][C];
        copyIceGrid(copiedGrid);

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (copiedGrid[r][c] == 0) continue;

                int nearSea = 0;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (!isInside(nr, nc)) continue;
                    if (copiedGrid[nr][nc] != 0) continue;

                    nearSea++;
                }

                icesGrid[r][c] = Math.max(icesGrid[r][c] - nearSea, 0);
            }
        }
    }

    private static void copyIceGrid(int[][] map) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = icesGrid[r][c];
            }
        }
    }

    private static boolean isInside(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        icesGrid = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                icesGrid[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}