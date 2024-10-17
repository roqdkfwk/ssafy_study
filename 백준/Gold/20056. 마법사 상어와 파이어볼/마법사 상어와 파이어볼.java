import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Ball {
        int R, C;
        int M, S, D;

        public Ball(int R, int C, int M, int S, int D) {
            this.R = R;
            this.C = C;
            this.M = M;
            this.S = S;
            this.D = D;
        }
    }

    static int N, M, K;
    static List<Ball>[][] grid;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new List[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            grid[r][c].add(new Ball(r, c, m, s, d));
        }

        for (int k = 0; k < K; k++) {
            moveBalls();
            mergeBalls();
        }

        System.out.println(calculateTotalMass());
    }

    static void moveBalls() {
        List<Ball>[][] newGrid = new List[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                newGrid[r][c] = new ArrayList<>();
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (Ball ball : grid[r][c]) {
                    int nr = (r + dr[ball.D] * ball.S % N + N) % N;
                    int nc = (c + dc[ball.D] * ball.S % N + N) % N;
                    newGrid[nr][nc].add(new Ball(nr, nc, ball.M, ball.S, ball.D));
                }
            }
        }

        grid = newGrid;
    }

    static void mergeBalls() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c].size() > 1) {
                    int totalM = 0, totalS = 0;
                    boolean allEven = true, allOdd = true;
                    int count = grid[r][c].size();

                    for (Ball ball : grid[r][c]) {
                        totalM += ball.M;
                        totalS += ball.S;
                        if (ball.D % 2 == 0) allOdd = false;
                        else allEven = false;
                    }

                    int newM = totalM / 5;
                    int newS = totalS / count;

                    grid[r][c].clear();

                    if (newM > 0) {
                        for (int i = 0; i < 4; i++) {
                            int newD = (allEven || allOdd) ? i * 2 : i * 2 + 1;
                            grid[r][c].add(new Ball(r, c, newM, newS, newD));
                        }
                    }
                }
            }
        }
    }

    static int calculateTotalMass() {
        int totalMass = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (Ball ball : grid[r][c]) {
                    totalMass += ball.M;
                }
            }
        }
        return totalMass;
    }
}