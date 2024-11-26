import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] distance;
    static final int INF = 987654321;
    static TreeMap<Integer, List<Integer>> treemap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == -1 && B == -1) break;

            distance[A][B] = 1;
            distance[B][A] = 1;
        }

        floydWarshall();

        treemap = new TreeMap<>();
        for (int i = 1; i <= N; i++) {
            int score = 0;
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] != INF) {
                    score = Math.max(score, distance[i][j]);
                }
            }
            treemap.putIfAbsent(score, new ArrayList<>());
            treemap.get(score).add(i);
        }

        int minScore = treemap.firstKey();
        List<Integer> candidates = treemap.get(minScore);

        System.out.println(minScore + " " + candidates.size());
        for (int candidate : candidates) {
            System.out.print(candidate + " ");
        }
    }

    static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }
}