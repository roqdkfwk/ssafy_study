import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     // 입력 받기
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int start, int sCnt) {
        if (depth == 7) {
            if (sCnt >= 4 && isConnected()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;

            if (!visited[x][y]) {
                visited[x][y] = true;
                dfs(depth + 1, i + 1, map[x][y] == 'S' ? sCnt + 1 : sCnt);
                visited[x][y] = false;
            }
        }
    }

    static boolean isConnected() {
        int sx = -1, sy = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    sx = i;
                    sy = j;
                    break;
                }
            }
            if (sx != -1) break;
        }

        boolean[][] check = new boolean[5][5];
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        check[sx][sy] = true;
        count++;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && visited[nx][ny] && !check[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    check[nx][ny] = true;
                    count++;
                }
            }
        }

        return count == 7;
    }
}