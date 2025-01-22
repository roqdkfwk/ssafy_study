import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[] numArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        InputHandler();

        twoPointer();

        printResult();
    }

    private static void printResult() {
        System.out.println(sb.toString().trim());
    }

    private static void twoPointer() {
        int left = 0;
        int right = N - 1;
        int leftNum = numArr[left];
        int rightNum = numArr[right];
        int closetSum = Integer.MAX_VALUE;

        while (left < right) {
            int sum = numArr[left] + numArr[right];

            if (sum == 0) {
                sb.append(numArr[left]).append(" ").append(numArr[right]);
                return;
            }

            if (Math.abs(sum) < closetSum) {
                leftNum = numArr[left];
                rightNum = numArr[right];
                closetSum = Math.abs(sum);
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        sb.append(leftNum).append(" ").append(rightNum);
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        numArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numArr);
    }
}