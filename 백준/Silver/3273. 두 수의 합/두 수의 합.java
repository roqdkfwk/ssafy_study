import java.util.*;
import java.io.*;
public class Main {

    static int N, X;
    static int[] numArr;
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
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int num = numArr[left] + numArr[right];

            if (num == X) {
                answer++;
                right--;
            } else if (num < X) {
                left++;
            } else {
                right--;
            }
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);

        X = Integer.parseInt(br.readLine());
    }
}