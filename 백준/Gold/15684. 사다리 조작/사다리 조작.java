import java.util.*;
import java.io.*;
public class Main {

    static int N, M, H;
    static boolean[][] ladder;
    static int answer = 4;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로선의 개수
        M = Integer.parseInt(st.nextToken());   // 놓여 있는 가로선의 개수
        H = Integer.parseInt(st.nextToken());   // 놓을 수 있는 가로선의 개수

        ladder = new boolean[H + 1][N + 1];     // ladder[점선 위치][왼쪽 사다리 번호]
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            ladder[A][B] = true;
        }
    }

    private static void solution() {
        dfs(1, 1, 0);
    }

    private static void dfs(int par, int ver, int count) {
        if (answer <= count) return;

        if (validStatus()) {
            answer = count;
            return;
        }

        // 이미 3개 이상의 사다리를 설치했을 때는 더 살펴볼 필요가 없음
        if (count == 3) return;

        for (int row = par; row <= H; row++, ver = 1) {
            for (int col = ver; col < N; col++) {
                if (ladder[row][col] || ladder[row][col - 1] || ladder[row][col + 1]) continue;

                ladder[row][col] = true;
                dfs(par, ver + 1, count + 1);
                ladder[row][col] = false;
            }
        }
    }

    /**
     * 사다리의 상태를 확인
     */
    private static boolean validStatus() {
        for (int col = 1; col <= N; col++) {
            int nowCol = col;
            for (int row = 1; row <= H; row++) {
                if (ladder[row][nowCol]) nowCol++;
                else if (ladder[row][nowCol - 1]) nowCol--;
            }

            if (nowCol != col) {
                return false;
            }
        }

        return true;
    }

    private static void printResult() {
        System.out.println(answer > 3 ? -1 : answer);
    }
}