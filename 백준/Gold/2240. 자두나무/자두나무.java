import java.io.*;
import java.util.*;

public class Main {

    static int T, W;
    static int[] plums;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        int result = 0;
        for (int moves = 0; moves <= W; moves++) {
            result = Math.max(result, Math.max(dp[T][moves][0], dp[T][moves][1]));
        }

        System.out.println(result);
    }

    private static void Solution() {
        for (int time = 2; time <= T; time++) {
            calculateDP(time);
        }
    }

    private static void calculateDP(int time) {
        // 시작 이후 한 번도 움직이지 않은 경우
        dp[time][0][0] = dp[time - 1][0][0] + (plums[time] == 1 ? 1 : 0);

        // 이동이 가능한 경우들
        for (int moves = 1; moves <= W; moves++) {
            // 1번 나무에서의 최대값 계산
            calculatePosition(time, moves, 0);

            // 2번 나무에서의 최대값 계산
            calculatePosition(time, moves, 1);
        }
    }

    private static void calculatePosition(int time, int moves, int pos) {
        // 현재 위치에 머무르는 경우
        int stay = dp[time - 1][moves][pos] + (plums[time] == pos + 1 ? 1 : 0);

        // 다른 위치에서 현재 위치로 이동해온 경우
        int move = dp[time - 1][moves - 1][1 - pos] + (plums[time] == pos + 1 ? 1 : 0);

        // 두 경우 중 최대값 선택
        dp[time][moves][pos] = Math.max(stay, move);
    }

    static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        plums = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[T + 1][W + 1][2];

        // 초기값 설정
        if (plums[1] == 1) {
            dp[1][0][0] = 1;  // 1번 나무에 있을 때
            dp[1][1][1] = 0;  // 2번 나무로 이동했을 때
        } else {
            dp[1][0][0] = 0;  // 1번 나무에 있을 때
            dp[1][1][1] = 1;  // 2번 나무로 이동했을 때
        }
    }
}