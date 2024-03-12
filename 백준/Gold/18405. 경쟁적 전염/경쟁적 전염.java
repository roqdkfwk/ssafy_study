import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int N;    // 시험관의 크기
    static int K;    // 바이러스 종류의 개수
    static int[][] map;
    static boolean[][] visit; 
    static int S;    // 시간초
    static int X;
    static int Y;
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        simulateSpread();
        System.out.println(map[X][Y]);
    }

    static void simulateSpread() {
        Queue<Location> queue = new ArrayDeque<>();

        // 초기 바이러스 위치 큐에 추가
        for (int i = 1; i <= K; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == i) queue.offer(new Location(r, c, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Location current = queue.poll();
            int r = current.row;
            int c = current.col;
            int time = current.time;

            if (time == S) break; // 시간 초과시 종료

            for (int d = 0; d < 4; d++) {
                int rNow = r + dr[d];
                int cNow = c + dc[d];

                if (rNow >= 0 && rNow < N && cNow >= 0 && cNow < N && map[rNow][cNow] == 0) {
                    map[rNow][cNow] = map[r][c]; // 바이러스 전파
                    queue.offer(new Location(rNow, cNow, time + 1));
                }
            }
        }
    }

    static class Location {
        int row, col, time;

        Location(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}