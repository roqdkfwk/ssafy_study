import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long T;
    static ArrayList<Integer> A;
    static int biggerCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        A.sort(null);
        for (int i = 0; i < K; i++) {
            int left = 0;
            int right = A.size();

            while (left < right) {
                int mid = (left + right) / 2;

                if (A.get(mid) >= T) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (right == 0) {
                System.out.println(T);
                return;
            }
            T += A.get(right - 1);
            A.remove(right - 1);
        }

        System.out.println(T);
    }
}