import java.util.*;
import java.io.*;
public class Main {

    static int N, D, K, C;
    static int[] sushi;
    static Map<Integer, Integer> eatSushi;
    static int freeIndex = -1;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 접시의 수
        D = Integer.parseInt(st.nextToken());   // 초밥의 가짓수
        K = Integer.parseInt(st.nextToken());   // 연속 접시
        C = Integer.parseInt(st.nextToken());   // 쿠폰 번호

        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());

            if (sushi[i] == C) {
                freeIndex = i;
            }
        }
    }

    private static void solution() {
        eatSushi = new HashMap<>();
        if (freeIndex == -1) eatSushi.put(0, 1);
        else eatSushi.put(sushi[freeIndex], 1);

        for (int i = 0; i < K; i++) {
            eatSushi.put(sushi[i], eatSushi.getOrDefault(sushi[i], 0) + 1);
        }

        for (int i = 0; i < N; i++) {
            if (eatSushi.get(sushi[i]) == 1) {
                eatSushi.remove(sushi[i]);
            } else {
                eatSushi.put(sushi[i], eatSushi.get(sushi[i]) - 1);
            }

            eatSushi.put(sushi[(i + K) % N], eatSushi.getOrDefault(sushi[(i + K) % N], 0) + 1);
            answer = Math.max(answer, eatSushi.size());
        }
    }

    private static void printResult() {
        System.out.println(answer);
    }
}