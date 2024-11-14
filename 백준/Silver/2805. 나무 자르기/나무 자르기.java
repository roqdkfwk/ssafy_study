import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 필요한 나무의 길이

        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken()); // 나무 높이 입력
        }

        int left = 0;
        int right = Arrays.stream(trees).max().getAsInt(); // 최대 나무 높이

        while (left < right) {
            int mid = (left + right + 1) / 2; // 절단기 높이
            long totalLength = 0;

            for (int tree : trees) {
                if (tree > mid) {
                    totalLength += tree - mid;
                }
            }

            if (totalLength >= M) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }
}