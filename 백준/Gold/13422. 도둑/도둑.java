import java.util.*;
import java.io.*;
public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 집의 개수
            int M = Integer.parseInt(st.nextToken());   // 연속된 집의 개수
            int K = Integer.parseInt(st.nextToken());   // 돈의 양

            int[] houses = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                houses[i] = Integer.parseInt(st.nextToken());
            }

            solution(N, M, K, houses);
        }
    }

    private static void solution(int N, int M, int LIMIT, int[] houses) {
        int answer = 0;

        int sum = 0;
        for (int i = 0; i < M; i++) {
            sum += houses[i];
        }

        if (sum < LIMIT) answer++;
        
        if (N == M) {
            sb.append(answer).append("\n");
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            sum -= houses[i];
            sum += houses[(i + M) % N];

            if (sum < LIMIT) answer++;
        }

        sb.append(answer).append("\n");
    }

    private static void printResult() {
        System.out.println(sb.toString());
    }
}