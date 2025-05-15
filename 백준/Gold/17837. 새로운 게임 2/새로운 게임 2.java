import java.util.*;
import java.io.*;

public class Main {

    static class Piece {
        int num, r, c, d;

        public Piece(int num, int r, int c, int d) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int N, K;
    static int[][] grid;
    static List<Piece> pieces;
    static List<Integer>[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();

        int answer = solution();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        pieces = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            Piece piece = new Piece(i, r, c, d);
            pieces.add(piece);
            map[r][c].add(i);
        }
    }

    private static int solution() {
        for (int t = 1; t <= 1000; t++) {
            for (int i = 0; i < K; i++) {
                Piece piece = pieces.get(i);
                int r = piece.r;
                int c = piece.c;
                int d = piece.d;

                int idx = map[r][c].indexOf(piece.num);
                List<Integer> moving = new ArrayList<>(map[r][c].subList(idx, map[r][c].size()));
                map[r][c] = new ArrayList<>(map[r][c].subList(0, idx));

                int nr = r + dr[d];
                int nc = c + dc[d];

                // 격자를 벗어나거나 || 파란색인 경우
                if (!isInside(nr, nc) || grid[nr][nc] == 2) {
                    piece.d = reverseDirection(d);
                    d = piece.d;
                    nr = r + dr[d];
                    nc = c + dc[d];

                    // 방향 바꾼 후도 파란색 or 범위 밖이면 이동 취소
                    if (!isInside(nr, nc) || grid[nr][nc] == 2) {
                        map[r][c].addAll(moving);
                        continue;
                    }
                }

                // 빨간색이면 순서 뒤집음
                if (grid[nr][nc] == 1) {
                    Collections.reverse(moving);
                }

                for (int m : moving) {
                    pieces.get(m).r = nr;
                    pieces.get(m).c = nc;
                    map[nr][nc].add(m);
                }

                // 말이 4개 이상 쌓이면 종료
                if (map[nr][nc].size() >= 4) {
                    return t;
                }
            }
        }
        return -1; // 1000턴 초과
    }

    private static int reverseDirection(int d) {
        if (d == 0) return 1;
        else if (d == 1) return 0;
        else if (d == 2) return 3;
        else return 2;
    }

    private static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}