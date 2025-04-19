import java.util.*;
import java.io.*;
public class Main {

    static class Star implements Comparable<Star> {
        int r, c;

        public Star(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Star s) {
            if (this.r == s.r) {
                return Integer.compare(this.c, s.c);
            }
            return Integer.compare(this.r, s.r);
        }
    }

    static int R, C, L, K;
    static List<int[]> stars;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());   // 가로
        R = Integer.parseInt(st.nextToken());   // 세로
        L = Integer.parseInt(st.nextToken());   // 트램펄린
        K = Integer.parseInt(st.nextToken());   // 별똥별의 개수

        stars = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
    }

    private static void solution() {
        answer = K;
        for (int[] s1 : stars) {
            for (int[] s2 : stars) {
                answer = Math.min(answer, countStars(s1[0], s2[1]));
            }
        }
    }

    private static int countStars(int r, int c) {
        int result = K;
        for (int[] star : stars) {
            if (r <= star[0] && star[0] <= r + L && c <= star[1] && star[1] <= c + L) {
                result--;
            }

            if (result == 0) return 0;
        }
        return result;
    }

    private static void printResult() {
        System.out.println(answer);
    }
}