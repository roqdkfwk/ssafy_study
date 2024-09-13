import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static long[] prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        prefix = new long[N + 1];
        prefix[0] = 0;
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 1;
        int length = Integer.MAX_VALUE;
        while (start < N) {
            long sum = prefix[end] - prefix[start];
            if (sum >= S) {
                length = Math.min(length, end - start);
                start++;
            } else {
            	if (end == N) break;
                end++;
            }
        }

        int answer = length == Integer.MAX_VALUE ? 0 : length;
        System.out.println(answer);
    }   // main
}