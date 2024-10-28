import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] A;
    static int[] dr = {0, 1, 0, -1};   // 좌 하 우 상
    static int[] dc = {-1, 0, 1, 0};   // 좌 하 우 상\
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도는 2번 단위로 이동 거리가 길어진다.
        // 토네이도의 시작 위치 설정
        int r = N / 2;
        int c = N / 2;

        // 토네이도가 이동하면 다음 칸에 있는 모래가 날린다.
        // 현재 위치한 칸 말고 다음 칸 기준으로 고려하면 될듯
        // 밖으로 나가는 모래의 양은 계속 체크
        // 이동 거리는 1 1 2 2 3 3 4 4 ...
        answer = 0;
        int move = 1;   // 이동 거리
        int d = 0;      // 방향
//        while (!(r == 0 && c == 0)) {
//            d %= 4;
//
//            for (int i = 0; i < move; i++) {
//                r += dr[d];
//                c += dc[d];
//
//                if (!isInside(r, c)) break;
//                // 모래를 날리고 밖으로 나가는 모래의 양 체크
//                iceCream(r, c, d);
//            }
//            d = (d + 1) % 4;  // 여기로 이동
//
//            for (int i = 0; i < move; i++) {
//                r += dr[d];
//                c += dc[d];
//
//                if (!isInside(r, c)) break;
//                // 모래를 날리고 밖으로 나가는 모래의 양 체크
//                iceCream(r, c, d);
//            }
//            d = (d + 1) % 4;  // 여기로 이동
//
//            move++;
//        }
        while (true) {
            d %= 4;

            for (int i = 0; i < move; i++) {
                r += dr[d];
                c += dc[d];

                if (!isInside(r, c)) break;
                // 모래를 날리고 밖으로 나가는 모래의 양 체크
                iceCream(r, c, d);
                if (r == 0 && c == 0) {
                    System.out.println(answer);
                    return;
                }
            }
            d++;
            for (int i = 0; i < move; i++) {
                r += dr[d];
                c += dc[d];

                if (!isInside(r, c)) break;
                // 모래를 날리고 밖으로 나가는 모래의 양 체크
                iceCream(r, c, d);
            }
            d++;

            move++;
        }
    }

    static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void iceCream(int r, int c, int d) {
        // (r, c)를 기준으로 계산
        int[] percent = {1, 1, 7, 7, 2, 2, 10, 10, 5};
        int[][] left = {{-1, 1}, {1, 1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, -1}, {1, -1}, {0, -2}};
        int[][] right = {{-1, -1}, {1, -1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, 1}, {1, 1}, {0, 2}};
        int[][] up = {{1, -1}, {1, 1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {-1, -1}, {-1, 1}, {-2, 0}};
        int[][] down = {{-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {1, -1}, {1, 1}, {2, 0}};

        // 모래의 양
        int sand = A[r][c];
        int remainSand = A[r][c];
        A[r][c] = 0;

        if (d == 0) {           // 왼쪽으로 이동중인 경우
            for (int i = 0; i < 9; i++) {
                int nr = r + left[i][0];
                int nc = c + left[i][1];

                if (isInside(nr, nc)) {
                    // 원래 있던 모래에 날아온 모래를 더한다.
                    A[nr][nc] += (sand * percent[i]) / 100;
                } else {
                    answer += (sand * percent[i]) / 100;
                }

                remainSand -= (sand * percent[i]) / 100;
            }
            if (isInside(r, c - 1)) {
                A[r][c - 1] += remainSand;
            } else {
                answer += remainSand;
            }
        } else if (d == 1) {    // 아래쪽으로 이동중인 경우
            for (int i = 0; i < 9; i++) {
                int nr = r + down[i][0];
                int nc = c + down[i][1];

                if (isInside(nr, nc)) {
                    // 원래 있던 모래에 날아온 모래를 더한다.
                    A[nr][nc] += (sand * percent[i]) / 100;
                } else {
                    answer += (sand * percent[i]) / 100;
                }

                remainSand -= (sand * percent[i]) / 100;
            }
            if (isInside(r + 1, c)) {
                A[r + 1][c] += remainSand;
            } else {
                answer += remainSand;
            }
        } else if (d == 2) {    // 오른쪽으로 이동중인 경우
            for (int i = 0; i < 9; i++) {
                int nr = r + right[i][0];
                int nc = c + right[i][1];

                if (isInside(nr, nc)) {
                    // 원래 있던 모래에 날아온 모래를 더한다.
                    A[nr][nc] += (sand * percent[i]) / 100;
                } else {
                    answer += (sand * percent[i]) / 100;
                }

                remainSand -= (sand * percent[i]) / 100;
            }
            if (isInside(r, c + 1)) {
                A[r][c + 1] += remainSand;
            } else {
                answer += remainSand;
            }
        } else {                // 위쪾으로 이동중인 경우
            for (int i = 0; i < 9; i++) {
                int nr = r + up[i][0];
                int nc = c + up[i][1];

                if (isInside(nr, nc)) {
                    // 원래 있던 모래에 날아온 모래를 더한다.
                    A[nr][nc] += (sand * percent[i]) / 100;
                } else {
                    answer += (sand * percent[i]) / 100;
                }

                remainSand -= (sand * percent[i]) / 100;
            }
            if (isInside(r - 1, c)) {
                A[r - 1][c] += remainSand;
            } else {
                answer += remainSand;
            }
        }
    }
}