import java.util.*;
import java.io.*;
public class Main {

    static int D, N;
    static int[] oven, pizza;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        oven = new int[D + 1];
        oven[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= D; i++) {
            oven[i] = Math.min(Integer.parseInt(st.nextToken()), oven[i - 1]);
        }

        st = new StringTokenizer(br.readLine());
        pizza = new int[N];
        for (int i = 0; i < N; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solution() {
        int index = 0;
        for (int depth = D; depth > 0; depth--) {
            if (oven[depth] < pizza[index]) continue;

            index++;
            if (index == N) {
                answer = depth;
                return;
            }
        }
    }

    private static void printResult() {
        System.out.println(answer);
    }
}