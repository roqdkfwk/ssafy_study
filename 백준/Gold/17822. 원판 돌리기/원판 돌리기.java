import java.io.*;
import java.util.*;
public class Main {

    static int N, M, T;
    static int X, D, K;
    static int[][] operator;
    static int[][] circle;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int numberSum, answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void Solution() {
        // X : operator[t][0]
        // D : operator[t][1]
        // K : operator[t][2]
        for (int t = 0; t < T; t++) {
            // 1. 회전
            int radius = operator[t][0];
            while (radius <= N) {
                if (operator[t][1] == 0) clockWise(radius - 1, operator[t][2]); // 시계 방향 회전
                else counterClockWise(radius - 1, operator[t][2]);              // 반시계 방향 회전

                radius += operator[t][0];
            }
            // 2. 인접한 수를 지우거나 평균을 구한다.
            findSameNum();
        }
        summationNumber();
    }

    private static void summationNumber() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += circle[i][j];
            }
        }
    }

    /**
     * 시계 방향 회전 메서드는
     * 단일 책임 원칙에 따라 원판을 회전시키는 로직만 갖고 원판의 번호를 전달받는 것이 맞을까
     * 아니면 X의 배수 번호를 갖는 원판을 모두 회전하는 로직까지 가져도 괜찮을까?
     */
    // 시계 방향 회전 - 커지는 방향
    private static void clockWise(int num, int move) {
        int[] tmpCircle = new int[M];
        for (int i = 0; i < M; i++) {
            tmpCircle[(i + move) % M] = circle[num][i];
        }

        for (int i = 0; i < M; i++) {
            circle[num][i] = tmpCircle[i];
        }
    }

    // 반시계 방향 회전 - 작아지는 방향
    private static void counterClockWise(int num, int move) {
        int[] tmpCircle = new int[M];
        for (int i = 0; i < M; i++) {
            tmpCircle[(i - move + M) % M] = circle[num][i];
        }

        for (int i = 0; i < M; i++) {
            circle[num][i] = tmpCircle[i];
        }
    }

    private static void findSameNum() {
        Queue<int[]> searchQ = new LinkedList<>();  // bfs에 사용할 큐
        Queue<int[]> answerQ = new LinkedList<>();  // 같은 숫자의 위치를 담을 큐
        boolean[][] visited = new boolean[N][M];

        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (circle[i][j] == 0) continue;    // 지워진 수인 경우

                searchQ.add(new int[] {i, j});
                answerQ.add(new int[] {i, j});
                visited[i][j] = true;
                while (!searchQ.isEmpty()) {
                    int[] curr = searchQ.poll();

                    for (int d = 0; d < 4; d++) {
                        int nr = curr[0] + dr[d];
                        int nc = (curr[1] + dc[d] + M) % M;

                        if (nr < 0 || nr >= N || visited[nr][nc]) continue;
                        if (circle[curr[0]][curr[1]] != circle[nr][nc]) continue;

                        searchQ.add(new int[] {nr, nc});
                        answerQ.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }

                if (answerQ.size() > 1) {
                    flag = true;                                // 인접하면서 같은 수가 있는 경우

                    while (!answerQ.isEmpty()) {
                        int[] curr = answerQ.poll();

                        circle[curr[0]][curr[1]] = 0;   // 숫자들이 지워진 위치는 0을 넣는다.
                    }
                }
                answerQ = new LinkedList<>();
            }
        }

        if (!flag) {    // 인접하면서 같은 수가 한 번도 없었던 경우
            int sum = 0;
            int count = 0;
            double avg = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (circle[i][j] == 0) continue;

                    sum += circle[i][j];
                    count++;
                }
            }

            avg = sum / (double)count;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (circle[i][j] == 0) continue;

                    if (circle[i][j] > avg) circle[i][j]--;         // 평균보다 큰 경우 1만큼 뺀다.
                    else if (circle[i][j] < avg) circle[i][j]++;    // 평균보다 작은 경우 1만큼 더한다.
                }
            }
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
                numberSum += circle[i][j];
            }
        }

        operator = new int[T][3];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            operator[i][0] = Integer.parseInt(st.nextToken());
            operator[i][1] = Integer.parseInt(st.nextToken());
            operator[i][2] = Integer.parseInt(st.nextToken());
        }
    }
}