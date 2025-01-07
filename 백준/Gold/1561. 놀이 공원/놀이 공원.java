import java.util.*;
import java.io.*;
public class Main {

    static int N, M;
    static int[] times;
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void Solution() {
        if (N <= M) {
            answer = N;
            return;
        }

        long low = 0;
        long high = (long)N * 30;
        while (low < high) {
            long mid = low + (high - low) / 2;
            // 시작하자마자 M명은 탑승할 수 있다.
            long child = M;

            for (int time : times) {
                child += mid / time;
            }

            if (child >= N) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        long totalKids = M;
        for (int time : times)
            totalKids += (low - 1) / time;

        for (int i = 0; i < M; i++) {
            if (low % times[i] == 0) {
                totalKids++;
                if (totalKids == N) {
                    answer = i + 1;
                    break;
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

        times = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            times[i] = Integer.parseInt(st.nextToken());
    }
}