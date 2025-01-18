import java.io.*;
public class Main {

    static int T;
    static long[] numArr;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    private static void Solution() {
        for (int t = 0; t < T; t++) {
            long N = numArr[t];
            int count = 0;

            for (long k = 2; k * (k - 1) / 2 < N; k++) {
                long remainder = N - k * (k - 1) / 2;
                if (remainder % k == 0) {
                    count++;
                }
            }
            answer[t] = count;
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        numArr = new long[T];
        for (int t = 0; t < T; t++) {
            numArr[t] = Long.parseLong(br.readLine()) * 2;
        }

        answer = new int[T];
    }
}