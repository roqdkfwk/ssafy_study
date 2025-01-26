import java.util.*;
import java.io.*;
public class Main {

    static int N, K;
    static int[] sensors;           // 센서의 위치를 담을 배열
    static Integer[] differences;   // 인접한 센서와의 간격을 담을 배열, Collections.reverseOrder()를 사용하기 위해서 Integer로 사용
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
        if (N <= K) return;

        for (int i = K - 1; i < N - 1; i++) {
            answer += differences[i];
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        if (N <= K) return;

        sensors = new int[N];
        differences = new Integer[N - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensors);

        for (int i = 0; i < N - 1; i++) {
            differences[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(differences, Collections.reverseOrder());
    }
}