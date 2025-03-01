import java.util.*;
import java.io.*;
public class Main {

    static int C, N;
    static int[] chickens;
    static int[][] cows;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int index = 0;
        for (int time : chickens) {
            while (index < N && cows[index][0] <= time) {
                pq.add(cows[index]);
                index++;
            }

            // 닭이 도움을 줄 수 없는 소들
            while (!pq.isEmpty() && pq.peek()[1] < time) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                pq.poll();
                answer++;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());   // 닭고기
        N = Integer.parseInt(st.nextToken());   // 소고기

        chickens = new int[C];
        for (int i = 0; i < C; i++) {
            chickens[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(chickens);

        cows = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });
    }
}