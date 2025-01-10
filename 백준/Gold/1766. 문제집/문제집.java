import java.util.*;
import java.io.*;
public class Main {

    static int N, M;
    static ArrayList<Integer>[] problems;
    static int[] degree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(sb.toString().trim());
    }

    private static void Solution() {
        sb = new StringBuilder();
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");

            for (int problem : problems[curr]) {
                degree[problem]--;

                if (degree[problem] == 0) {
                    pq.add(problem);
                }
            }
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        problems = new ArrayList[N + 1];
        degree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            problems[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            problems[A].add(B);
            degree[B]++;
        }
    }
}