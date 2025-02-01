import java.util.*;
import java.io.*;
public class Main {

    static int N, K;
    static ArrayList<Integer> lessThanK, atLeastK;
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        twoPointer();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer == 0 ? -1 : answer);
    }

    private static void twoPointer() {
        Collections.sort(lessThanK);

        int left = 0;
        int right = lessThanK.size() - 1;
        while (left < right) {
            int num = lessThanK.get(left) + lessThanK.get(right);

            if (num >= K) {
                answer++;
                left++;
                right--;
            } else {
                left++;
            }
        }
        answer += atLeastK.size();
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        lessThanK = new ArrayList<>();
        atLeastK =  new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num < K) lessThanK.add(num);
            else atLeastK.add(num);
        }
    }
}