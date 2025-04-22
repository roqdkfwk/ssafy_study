import java.util.*;
import java.io.*;
public class Main {

    static class Connection implements Comparable<Connection> {
        int from, to, length;

        public Connection(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Connection c) {
            return Integer.compare(this.length, c.length);
        }
    }

    static int N;
    static PriorityQueue<Connection> connections;
    static int[] parent;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        connections = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < str.length(); j++) {
                answer += convertLength(str.charAt(j));

                if (i == j || str.charAt(j) == '0') continue;

                int length = convertLength(str.charAt(j));
                connections.add(new Connection(i, j, length));
            }
        }
    }

    private static int convertLength(char ch) {
        if (ch == '0') return 0;

        if ('a' <= ch && ch <= 'z') {
            return ch - 'a' + 1;
        }
        return ch - 'A' + 27;
    }

    private static void solution() {
        parent = new int[N];
        for (int i = 1; i < N; i++) {
            parent[i] = i;
        }

        int edges = 0;
        while (!connections.isEmpty() && edges < N - 1) {
            Connection connection = connections.poll();

            int A = findParent(connection.from);
            int B = findParent(connection.to);

            if (A == B) continue;

            union(A, B);

            edges++;
            answer -= connection.length;
        }

        for (int i = 1; i < N; i++) {
            if (findParent(i) != findParent(i - 1)) {
                answer = -1;
                return;
            }
        }
    }

    private static int findParent(int A) {
        if (A != parent[A]) {
            parent[A] = findParent(parent[A]);
        }
        return parent[A];
    }

    private static void union(int A, int B) {
        if (A <= B) {
            parent[B] = parent[A];
            return;
        }

        parent[A] = parent[B];
    }

    private static void printResult() {
        System.out.println(answer);
    }
}