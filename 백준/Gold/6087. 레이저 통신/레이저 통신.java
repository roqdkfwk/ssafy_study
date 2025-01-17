import java.util.*;
import java.io.*;
public class Main {
    
    static class Node implements Comparable<Node> {
        int r, c, dir, mirrors;

        public Node(int r, int c, int dir, int mirrors) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.mirrors = mirrors;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.mirrors, o.mirrors);
        }
    }

    static int R, C;
    static char[][] map;
    static int[][] center;
    static int[][] distance;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        InputHandler();


        Solution();
        printResult();
    }

    private static void printResult() {
        System.out.println(distance[center[1][0]][center[1][1]]);
    }

    private static void Solution() {
        dijkstra();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(center[0][0], center[0][1], -1, 0)); // 초기 위치
        distance[center[0][0]][center[0][1]] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distance[current.r][current.c] < current.mirrors) continue;

            for (int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                while (isValid(nr, nc)) {
                    int nextMirrors = current.mirrors;
                    if (current.dir != i && current.dir != -1) nextMirrors++;

                    if (nextMirrors < distance[nr][nc]) {
                        distance[nr][nc] = nextMirrors;
                        pq.add(new Node(nr, nc, i, nextMirrors));
                    }

                    nr += dr[i];
                    nc += dc[i];
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C && map[r][c] != '*';
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        distance = new int[R][C];
        center = new int[2][2];
        int index = 0;
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
                distance[r][c] = 10001;

                if (map[r][c] == 'C') {
                    center[index][0] = r;
                    center[index][1] = c;
                    index++;
                }
            }
        }
    }

}