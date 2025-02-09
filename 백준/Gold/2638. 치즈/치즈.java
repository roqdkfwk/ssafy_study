import java.io.*;
import java.util.*;

public class Main {

    private static class Position {
        int r;
        int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position pos = (Position) o;
            return r == pos.r && c == pos.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static int R, C;
    static int[][] grid;
    static Set<Position> cheese;
    static Map<Position, Integer> cheeseAtSurface;  // Map<치즈 위치, 접촉면 수>
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int cheeseCnt;
    static int spendTime;

    private static void printGrid() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(spendTime);
    }

    private static void Solution() {
        while (cheeseCnt > 0) {
            // 바깥쪽에 위치한 치즈를 찾는다.
            findCheeseAtSurface();

            // 찾은 치즈를 녹인다.
            meltCheese();

            spendTime++;
        }
    }

    private static void findCheeseAtSurface() {
        cheeseAtSurface = new HashMap<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;

        // 외부에서부터 순회
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (!isInside(nr, nc) || visited[nr][nc]) continue;

                if (grid[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                } else {
                    Position pos = new Position(nr, nc);
                    cheeseAtSurface.put(pos, cheeseAtSurface.getOrDefault(pos, 0) + 1);
                }
            }
        }
    }

    private static void meltCheese() {
        int meltedCheese = 0;
        for (Position pos : cheeseAtSurface.keySet()) {
            if (cheeseAtSurface.get(pos) >= 2) {
                grid[pos.r][pos.c] = 0;
                meltedCheese++;
                cheese.remove(pos);
            }
        }
        cheeseCnt -= meltedCheese;
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

        grid = new int[R][C];
        cheese = new HashSet<>();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());

                if (grid[r][c] == 1) {
                    cheese.add(new Position(r, c));
                    cheeseCnt++;
                }
            }
        }
    }
}