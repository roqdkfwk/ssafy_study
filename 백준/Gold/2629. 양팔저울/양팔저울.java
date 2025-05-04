import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] chu = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] gong = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            gong[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Boolean> dp = new HashMap<>();
        dp.put(0, true);
        for (int i = 0; i < N; i++) {
            Set<Integer> set = new HashSet<>(dp.keySet());
            for (Integer weight : set) {
                dp.put(chu[i], true);                       // i번째 추만 고려한 경우
                dp.put(weight + chu[i], true);              // i번째 추를 더한 경우
                dp.put(Math.abs(weight - chu[i]), true);    // i번째 추를 뺸 경우
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (dp.containsKey(gong[i])) answer.append("Y ");
            else answer.append("N ");
        }

        System.out.println(answer.toString().trim());
    }
}