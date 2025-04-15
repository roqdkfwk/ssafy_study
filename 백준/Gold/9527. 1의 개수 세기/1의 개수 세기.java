import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken()) - 1;
        long B = Long.parseLong(st.nextToken());

        long answer = countOneLessThanNum(B) - countOneLessThanNum(A);

        System.out.println(answer);
    }

    private static long countOneLessThanNum(long num) {
        long result = 0;
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i <= 55; i++) {
            if ((num & (1L << i)) != 0) {
                dq.addFirst(i);
            }
        }

        int index = 0;
        for (int exp : dq) {
            if (exp == 0) {
                result += index + 1;
                return result;
            }

            result += (1L << exp - 1) * exp + index * (1L << exp);
            result++;
            index++;
        }
        return result;
    }
}