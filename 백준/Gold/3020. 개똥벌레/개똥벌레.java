import java.util.*;
import java.io.*;
public class Main {

    static int N, H;        // 길이, 높이
    static int[] up, down;  // 종유석, 석순
    static int min, answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(min + " " + answer);
    }

    private static void Solution() {
        min = Integer.MAX_VALUE;
        for(int i = 1; i <= H; i++){
            int d = calc(down, i);
            int u = calc(up, H - i + 1);

            if(min > d + u){
                min = d + u;
                answer = 1;
            }
            else if (min == d + u){
                answer++;
            }
        }
    }

    private static int calc(int[] arr, int height) {
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;

            if (arr[mid] >= height) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr.length - right;
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[N / 2];
        down = new int[N / 2];

        for(int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                down[i / 2] = Integer.parseInt(br.readLine());
            } else {
                up[i / 2] = Integer.parseInt(br.readLine());
            }
        }
        Arrays.sort(up);
        Arrays.sort(down);
    }
}