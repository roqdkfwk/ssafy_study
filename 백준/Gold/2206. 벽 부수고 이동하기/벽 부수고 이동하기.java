import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visit;  // visit[wall][r][c]
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Position {
        int r, c, length;
        int wall;  // 부순 벽의 개수

        Position(int r, int c, int length, int wall) {
            this.r = r;
            this.c = c;
            this.length = length;
            this.wall = wall;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[2][N][M];  // wall이 0 또는 1인 경우를 나누어 방문 체크

        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0, 1, 0));  // 시작점, 길이 1, 부순 벽 0개
        visit[0][0][0] = true;  // wall=0 상태로 (0,0) 방문 체크

        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            // 도착점에 도달한 경우
            if (cur.r == N-1 && cur.c == M-1) {
                return cur.length;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (!isInside(nr, nc)) continue;

                // 다음 칸이 벽이 아닌 경우
                if (map[nr][nc] == 0) {
                    // 해당 상태(벽을 부순 횟수)로 방문하지 않은 경우만 진행
                    if (!visit[cur.wall][nr][nc]) {
                        queue.offer(new Position(nr, nc, cur.length + 1, cur.wall));
                        visit[cur.wall][nr][nc] = true;
                    }
                }
                // 다음 칸이 벽인 경우
                else {
                    // 아직 벽을 부순 적이 없는 경우에만 진행
                    if (cur.wall == 0 && !visit[1][nr][nc]) {
                        queue.offer(new Position(nr, nc, cur.length + 1, 1));
                        visit[1][nr][nc] = true;
                    }
                }
            }
        }

        return -1;
    }

    static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}