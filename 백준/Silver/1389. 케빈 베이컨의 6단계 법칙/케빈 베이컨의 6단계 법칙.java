import java.util.*;
import java.io.*;
public class Main {

    static int N, M;
    static int[][] friends;
    static int[] kbNumber;
    static TreeMap<Integer, List<Integer>> treemap;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(friends[i], INF);
        for (int i = 1; i <= N; i++) friends[i][i] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            friends[A][B] = friends[B][A] = 1;
        }

        floydWarshall();

        kbNumber = new int[N + 1];
        treemap = new TreeMap<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                kbNumber[i] += friends[i][j];

            treemap.putIfAbsent(kbNumber[i], new ArrayList<>());
            treemap.get(kbNumber[i]).add(i);
        }

        System.out.println(treemap.get(treemap.firstKey()).get(0));
    }

    static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (friends[i][j] > friends[i][k] + friends[k][j])
                        friends[i][j] = friends[i][k] + friends[k][j];
                }
            }
        }
    }
}