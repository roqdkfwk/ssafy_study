import java.util.*;
import java.io.*;
public class Main {

    static int R, C, T;
    static int[][][] grid;
    static Queue<int[]> queue;  // 미세먼지의 위치
    static int[][] cleaner;     // 공기 청정기의 위치
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = 2;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        grid = new int[2][R][C];
        queue = new LinkedList<>();
        cleaner = new int[2][2];
        int index = 0;
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[0][r][c] = Integer.parseInt(st.nextToken());
                answer +=  grid[0][r][c];

                if (grid[0][r][c] > 0) queue.offer(new int[] {r, c});
                if (grid[0][r][c] == -1) {
                    cleaner[index][0] = r;
                    cleaner[index][1] = c;
                    index++;
                }
            }
        }
    }

    private static void solution() {
        for (int t = 0; t < T; t++) {
            // 1. 미세먼지 확산
            grid[1] = spread(grid[0]);

            // 2. 공기 청정기 작동
            grid[0] = operateAirCleaner(grid[1]);

            // 3. 미세먼지 위치 저장
            findDust(grid[0]);
        }
    }

    private static int[][] spread(int[][] array) {
        int[][] resultGrid = new int[R][C];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int dust = array[curr[0]][curr[1]];
            int spreadDust = dust / 5;
            resultGrid[curr[0]][curr[1]] += dust;

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (!isValid(nr, nc)) continue;

                resultGrid[nr][nc] += spreadDust;
                resultGrid[curr[0]][curr[1]] -= spreadDust;
            }
        }

        return resultGrid;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C && !isAirCleaner(r, c);
    }

    private static boolean isAirCleaner(int r, int c) {
        return (r == cleaner[0][0] && c == cleaner[0][1]) || (r == cleaner[1][0] && c == cleaner[1][1]);
    }

    private static int[][] operateAirCleaner(int[][] array) {
        int[][] resultGrid = new int[R][C];

        int upperR = cleaner[0][0];
        int lowerR = cleaner[1][0];
        int nc = cleaner[0][1] + 1;

        // 미세먼지가 우로 이동
        while (nc < C - 1) {
            resultGrid[upperR][nc + 1] = array[upperR][nc];
            resultGrid[lowerR][nc + 1] = array[lowerR][nc];
            nc++;
        }

        // 미세먼지가 위로 이동
        while (upperR > 0) {
            resultGrid[upperR - 1][nc] = array[upperR][nc];
            upperR--;
        }

        // 미세먼지가 아래로 이동
        while (lowerR < R - 1) {
            resultGrid[lowerR + 1][nc] = array[lowerR][nc];
            lowerR++;
        }

        // 미세먼지가 좌로 이동
        while (nc > 0) {
            resultGrid[upperR][nc - 1] = array[upperR][nc];
            resultGrid[lowerR][nc - 1] = array[lowerR][nc];
            nc--;
        }

        // 미세먼지가 아래로 이동
        while (upperR < cleaner[0][0] - 1) {
            resultGrid[upperR + 1][nc] = array[upperR][nc];
            upperR++;
        }

        // 미세먼지가 위로 이동
        while (lowerR > cleaner[1][0] + 1) {
            resultGrid[lowerR - 1][nc] = array[lowerR][nc];
            lowerR--;
        }

        for (int r = 1; r < cleaner[0][0]; r++) {
            for (int c = 1; c < C - 1; c++) {
                resultGrid[r][c] = array[r][c];
            }
        }

        for (int r = cleaner[1][0] + 1; r < R - 1; r++) {
            for (int c = 1; c < C - 1; c++) {
                resultGrid[r][c] = array[r][c];
            }
        }

        answer -= array[upperR][nc];    // 공기 청정기로 들어간 미세먼지만큼 제거
        answer -= array[lowerR][nc];    // 공기 청정기로 들어간 미세먼지만큼 제거

        return resultGrid;
    }

    private static void findDust(int[][] array) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (array[r][c] <= 0) continue;

                queue.add(new int[] {r, c});
            }
        }
    }

    private static void printResult() {
        System.out.println(answer);
    }
}