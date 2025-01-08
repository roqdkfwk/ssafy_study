import java.util.*;
import java.io.*;
public class Main {

    static int N, M;
    static boolean[] smallStones;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void Solution() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 0});

        visited = new boolean[N + 1][N + 1];
        visited[1][0] = true;

        int moved = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            moved++;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();   // 현재 위치, 속도
                int position = current[0];      // 현재 위치
                int speed = current[1];         // 현재 속도

                for (int movingSpeed = speed - 1; movingSpeed <= speed + 1; movingSpeed++) {
                    if (movingSpeed <= 0) continue;             // 속도가 0보다 작을 수는 없다.

                    int nextPosition = position + movingSpeed;

                    if (nextPosition == N) {                    // 목표 위치에 도달한 경우
                        answer = moved;
                        return;
                    }

                    if (nextPosition >= N || smallStones[nextPosition] || visited[nextPosition][movingSpeed])
                        continue;

                    visited[nextPosition][movingSpeed] = true;
                    queue.add(new int[] {nextPosition, movingSpeed});
                }
            }
        }

        answer = -1;
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        smallStones = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            smallStones[Integer.parseInt(br.readLine())] = true;
        }
    }
}