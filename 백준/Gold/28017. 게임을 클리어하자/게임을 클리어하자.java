import java.io.*;
import java.util.*;
public class Main {

    static class Stage implements Comparable<Stage> {
        int stage, num, time;

        public Stage(int stage, int num, int time) {
            this.stage = stage;
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Stage s) {
            return Integer.compare(this.time, s.time);
        }
    }

    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Stage>[] pq = new PriorityQueue[N];
        for (int i = 0; i < N; i++) {
            pq[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                pq[i].add(new Stage(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        dp = new int[N][2];
        int[][] index = new int[N][2];
        List<Stage> list = new ArrayList<>(pq[0]);
        for (int i = 0; i < 2; i++) {
            Stage stg = pq[0].poll();

            index[0][i] = stg.num;
            dp[0][i] = stg.time;
        }

        for (int i = 1; i < N; i++) {
            list.set(0, pq[i].poll());
            list.set(1, pq[i].poll());
            for (int j = 0; j < 2; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = 0; k < 2; k++) {
                    if (list.get(j).num == index[i - 1][k]) continue;

                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + list.get(j).time);
                    index[i][j] = list.get(j).num;
                }
            }
        }

        System.out.println(Math.min(dp[N - 1][0], dp[N - 1][1]));
    }
}