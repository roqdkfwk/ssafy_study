import java.io.*;
import java.util.*;

public class Main {

    static int D, N, M;
    static int[] stones;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());   // 탈출구까지 거리
        N = Integer.parseInt(st.nextToken());   // 돌섬 개수
        M = Integer.parseInt(st.nextToken());   // 제거할 돌 개수

        stones = new int[N + 2];  // 0(시작), N개 돌, D(끝)
        stones[0] = 0;
        stones[N + 1] = D;

        for (int i = 1; i <= N; i++) {
            stones[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(stones);
    }

    private static void solution() {
        if (N == M) {
            answer = D;
            return;
        }

        if (M == 0) {
            for (int i = 0; i <= N + 1; i++) {
                answer = Math.min(answer, stones[i + 1] - stones[i]);
            }
        }
        
        int low = 1;
        int high = D;

        while (low < high) {
            int mid = (low + high) / 2;

            if (canCross(mid)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid;
            }
        }
    }

    private static boolean canCross(int minJump) {
        int removed = 0;
        int prev = 0;

        for (int i = 1; i < stones.length; i++) {
            int dist = stones[i] - stones[prev];

            if (dist < minJump) {
                removed++;  // 너무 가까우면 돌 제거
            } else {
                prev = i;   // 밟고 진행
            }
        }

        return removed <= M;
    }

    private static void printResult() {
        System.out.println(answer);
    }
}