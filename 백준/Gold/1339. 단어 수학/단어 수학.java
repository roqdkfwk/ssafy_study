import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Map<Character, Integer> map;
    static PriorityQueue<Integer> count;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        count = new PriorityQueue<>(Comparator.reverseOrder());

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char key = str.charAt(j);

                map.put(key, map.getOrDefault(key, 0) + (int)Math.pow(10, str.length() - j - 1));
            }
        }

        for (char ch : map.keySet()) {
            count.add(map.get(ch));
        }

        int num = 9;
        while (!count.isEmpty()) {
            answer += num * count.poll();
            num--;
        }

        System.out.println(answer);
    }
}