import java.util.*;
import java.io.*;
public class Main {

    static int[] direction = {-3, 3, -1, 1};
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static final String target = "123456780";
    static int answer;

    public static void main(String[] args) throws IOException {
        String firstStatus = init();

        answer = solution(firstStatus);

        printResult();
    }

    private static String init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String firstStatus = "";
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                firstStatus += st.nextToken();
            }
        }

        return firstStatus;
    }

    private static int solution(String str) {
        Map<String, Integer> map = new HashMap<>();
        map.put(str, 0);

        Queue<String> queue = new LinkedList<>();
        queue.add(str);

        while (!queue.isEmpty()) {
            String present = queue.poll();
            int index = present.indexOf('0');
            int moveCount = map.get(present);

            if (present.equals(target)) {
                return map.get(target);
            }

            for (int i = 0; i < 4; i++) {
                int nr = index / 3 + dr[i];
                int nc = index % 3 + dc[i];

                if (!isValid(nr, nc)) continue;

                int nextIndex = nr * 3 + nc;
                String nextStr = swap(index, nextIndex, present);

                if (map.containsKey(nextStr)) continue;

                map.put(nextStr, moveCount + 1);
                queue.add(nextStr);
            }
        }
        return -1;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3;
    }

    private static String swap(int index1, int index2, String str) {
        char c1 = str.charAt(index1);
        char c2 = str.charAt(index2);

        str = str.replace(c1, 't');
        str = str.replace(c2, c1);
        str = str.replace('t', c2);
        return str;
    }

    private static void printResult() {
        System.out.println(answer);
    }
}