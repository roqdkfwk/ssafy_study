import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[] numArr;
    static Map<Integer, List<int[]>> numMap;
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
        // 얼리리턴 조건
        if (N < 3) return;

        for (int i = 0; i < N; i++) {
            if (isGoodNum(i)) answer++;
        }
    }

    private static boolean isGoodNum(int index) {
        int target = numArr[index];
        int left = 0;
        int right = N - 1;

        while (left < right) {
            if (left == index) {    // 타겟 숫자와 같은 숫자인 경우
                left++;
                continue;
            }
            if (right == index) {   // 타겟 숫자와 같은 숫자인 경우
                right--;
                continue;
            }

            int sum = numArr[left] + numArr[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // 얼리리턴 조건
        if (N < 3) {
            answer = 0;
            return;
        }

        st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);
    }
}