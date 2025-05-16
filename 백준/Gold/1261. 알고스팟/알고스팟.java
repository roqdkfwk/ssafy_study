import java.util.*;
import java.io.*;

public class Main {

    static class Point {
        int r, c, cost;

        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] maze = new int[R][C];
        int[][] distance = new int[R][C];

        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                maze[r][c] = str.charAt(c) - '0';
                distance[r][c] = Integer.MAX_VALUE;
            }
        }

        Deque<Point> dq = new ArrayDeque<>();
        dq.add(new Point(0, 0, 0));
        distance[0][0] = 0;
        while (!dq.isEmpty()) {
            Point curr = dq.pollFirst();
            int r = curr.r;
            int c = curr.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                int cost = distance[r][c] + maze[nr][nc];
                if (distance[nr][nc] <= cost) continue;
                distance[nr][nc] = cost;

                if (maze[nr][nc] == 0) dq.addFirst(new Point(nr, nc, cost));
                else dq.add(new Point(nr, nc, cost));
            }
        }

        System.out.println(distance[R - 1][C - 1]);
    }
}