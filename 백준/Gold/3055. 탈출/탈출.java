import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // '.' : 비어있는 곳
    // '*' : 물이 차있는 곳
    // 'X' : 돌
    // 'D' : 비버의 굴
    // 'S' : 고슴도치의 위치
    // 고슴도치는 돌을 통과할 수 없고 물이 차있는 구역으로 이동할 수 없다.
    // 물은 돌을 통과할 수 없고 비버의 소굴로 이동할 수 없다.
    // 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. >> 물을 먼저 이동시킨 후 고슴도치를 이동시키면 될듯

    static int R, C;
    static char[][] grid;
    static Queue<int[]> water;
    static Queue<int[]> godo;
    static int[] dr = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};    // 상, 하, 좌, 우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][C];
        water = new LinkedList<>();
        godo = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            grid[r] = str.toCharArray();

            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '*') {    // 물이라면 큐에 추가
                    water.add(new int[] {r, c});
                } else if (grid[r][c] == 'S') {
                    godo.add(new int[] {r, c});
                }
            }
        }

        int size = 0;
        int answer = 0;
        int NR, NC;
        int[] curr;

        curr = godo.peek();
        for (int d = 0; d < 4; d++) {
            NR = curr[0] + dr[d];
            NC = curr[1] + dc[d];

            if (isOk(NR, NC)) {
                if (grid[NR][NC] == 'D') {
                    System.out.println(1);
                    return;
                }
            }
        }

        while (true) {  // 종료 조건 설정
            answer++;

            // 물 먼저 이동
            size = water.size();
            for (int i = 0; i < size; i++) {
                curr = water.poll();

                for (int d = 0; d < 4; d++) {
                    NR = curr[0] + dr[d];
                    NC = curr[1] + dc[d];

                    if (isOk(NR, NC)) {                     // 범위를 벗어나지 않고, 빈 칸이거나
                        if (grid[NR][NC] != 'D') {          // 비버의 굴이 아니라면
                            grid[NR][NC] = '*';             // 물을 채워주고
                            water.add(new int[] {NR, NC});  // 큐에 추가
                        }
                    }
                }
            }

            // 고슴도치 이동
            size = godo.size();
            for (int i = 0; i < size; i++) {
                curr = godo.poll();

                for (int d = 0; d < 4; d++) {
                    NR = curr[0] + dr[d];
                    NC = curr[1] + dc[d];

                    if (isOk(NR, NC) && grid[NR][NC] == '.') {  // 범위를 벗어나지 않고, 빈 칸이라면
                        for (int d2 = 0; d2 < 4; d2++) {
                            int NNR = NR + dr[d2];
                            int NNC = NC + dc[d2];

                            if (isOk(NNR, NNC) && grid[NNR][NNC] == 'D') {
                                System.out.println(++answer);
                                return;
                            }
                        }
                        grid[NR][NC] = 'S';             // 고슴도치를 위치시키고
                        godo.add(new int[] {NR, NC});   // 큐에 추가
                    }
                }
            }

            if (godo.isEmpty()) {
                System.out.println("KAKTUS");
                return;
            }
        }
    }

    static boolean isOk(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C && grid[r][c] != 'X' && grid[r][c] != '*';
    }
}