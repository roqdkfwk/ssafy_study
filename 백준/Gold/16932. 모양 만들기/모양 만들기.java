import java.util.*;
import java.io.*;

public class Main {

    static int R, C;
    static int[][] grid;
    static int[][] group;
    static int[] shapeSize;
    static Queue<int[]> zeroQueue;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int groupCount = 0;
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
        findShapes();

        while (!zeroQueue.isEmpty()) {
            int[] curr = zeroQueue.poll();
            answer = Math.max(answer, connectShape(curr[0], curr[1]));
        }
    }

    private static void findShapes() {
        group = new int[R][C];
        shapeSize = new int[R * C + 1];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1 && group[r][c] == 0) {
                    groupCount++;
                    shapeSize[groupCount] = bfs(r, c);
                }
            }
        }
    }

    private static int bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        group[r][c] = groupCount;

        int size = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            size++;

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (isValid(nr, nc) && grid[nr][nc] == 1 && group[nr][nc] == 0) {
                    group[nr][nc] = groupCount;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return size;
    }

    private static int connectShape(int r, int c) {
        Set<Integer> adjacentGroups = new HashSet<>();
        int totalSize = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (isValid(nr, nc) && group[nr][nc] > 0) {
                adjacentGroups.add(group[nr][nc]);
            }
        }

        for (int groupNum : adjacentGroups) {
            totalSize += shapeSize[groupNum];
        }

        return totalSize;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new int[R][C];
        zeroQueue = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());

                if (grid[r][c] == 0) {
                    zeroQueue.add(new int[]{r, c});
                }
            }
        }
    }
}