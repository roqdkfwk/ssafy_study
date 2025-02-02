import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static TreeMap<Integer, Boolean> trees; // TreeMap<나무의 위치, true>
    static int position = 0;                // 근성의 현재 위치
    static long answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void cleanUp() {
        while (true) {
            int key = position;
            Integer low = trees.floorKey(key);
            Integer high = trees.higherKey(key);

//            System.out.println();
//            System.out.println("key : " + key);
//            System.out.println("low : " + low);
//            System.out.println("high : " + high);
//            System.out.println(trees.keySet());

            if (low == null && high == null) return;

            if (low == null) {    // 가까운 위치가 오른쪽인 경우
                position = high;
                answer += Math.abs(high - key);
                trees.remove(high);
            } else if (high == null) {  // 가까운 위치가 왼쪽인 경우
                position = low;
                answer += Math.abs(low - key);
                trees.remove(low);
            } else if (Math.abs(low - key) <= Math.abs(high - key)) {    // 가까운 위치가 왼쪽인 경우
                position = low;
                answer += Math.abs(low - key);
                trees.remove(low);
            } else {    // 가까운 위치가 오른쪽인 경우
                position = high;
                answer += Math.abs(high - key);
                trees.remove(high);
            }
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        trees = new TreeMap<>();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int oper = Integer.parseInt(st.nextToken());

            if (oper == 1) {
                trees.put(Integer.parseInt(st.nextToken()), true);
            } else {
                cleanUp();
            }
        }
    }
}