import java.util.*;
import java.io.*;
public class Main {

    // 블록 그룹의 크기가 같은 경우
    // 무지개 블록의 수, 기준 블록의 행, 기준 블록의 열
    // 을 비교해야 하므로 사용자 정의 BlockGroup 클래스 생성
    static class BlockGroup implements Comparable<BlockGroup> {
        int r, c, color, size, rainbow;
        List<int[]> positions = new ArrayList<>();

        public BlockGroup(int r, int c, int color, int size, int rainbow) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.size = size;
            this.rainbow = rainbow;
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.size == o.size) {                              // 1. 크기가 같은 경우
                if (this.rainbow == o.rainbow) {                    // 2. 무지개 블록의 개수가 같은 경우
                    if (this.r == o.r)                              // 3. 행이 같은 경우
                        return Integer.compare(o.c, this.c);

                    return Integer.compare(o.r, this.r);            // 3. 행이 다른 경우
                }

                return Integer.compare(o.rainbow, this.rainbow);    // 2. 무지개 블록의 개수가 다른 경우
            }

            return Integer.compare(o.size, this.size);              // 1. 크기가 다른 경우
        }
    }

    static int N, M;
    static int[][] grid;
    static boolean[][] visited; // 방문처리
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static PriorityQueue<BlockGroup> pq;
    static int score;

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 1. 크기가 가장 큰 블록 그룹을 찾아야 하므로 우선순위 큐를 사용한다.
            // 블록 그룹을 pq에 담고
            pq = new PriorityQueue<>();
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (grid[r][c] > 0) findBlockGroup(r, c);
                }
            }

            if (pq.isEmpty()) break;

            // 2. 크기가 가장 큰 블록 그룹의 블록 제거 + 점수 획득
            BlockGroup blockGroup = pq.poll();
            score += acquireScore(blockGroup);
            for (int[] pos : blockGroup.positions)
                grid[pos[0]][pos[1]] = -2;

            // 3. 중력 작용
            gravity();

            // 4. 반시계 회전
            grid = rotateGrid();

            // 5. 중력 작용
            gravity();
        }

        System.out.println(score);
    }

    static int[][] rotateGrid() {
       int[][] rotated = new int[N][N];

       for (int r = 0; r < N; r++) {
           for (int c = 0; c < N; c++) {
               rotated[N - 1 - c][r] = grid[r][c];
           }
       }

       return rotated;
    }

    static void gravity() {
        for (int r = N - 2; r >= 0; r--) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] < 0) continue;   // 색깔이 있는 블록이거나 무지개 블록이 아닌 경우

                int row = r;
                while (row + 1 < N && grid[row + 1][c] == -2) row++;

                if (row == r) continue;         // 아래에 블록이 있는 경우

                grid[row][c] = grid[r][c];
                grid[r][c] = -2;
            }
        }
    }

    static int acquireScore(BlockGroup bg) {
        return bg.size * bg.size;
    }

    static void findBlockGroup(int r, int c) {
        BlockGroup blockGroup = new BlockGroup(r, c, grid[r][c], 1, 0);
        blockGroup.positions.add(new int[] {r, c});

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (!isValid(nr, nc, grid[r][c])) continue;

                blockGroup.positions.add(new int[] {nr, nc});
                blockGroup.size++;                              // 사이즈 1만큼 증가
                if (grid[nr][nc] == 0) blockGroup.rainbow++;    // 무지개인 경우

                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0)
                    visited[i][j] = false;
            }
        }

        // 블록 그룹의 사이즈가 2 이상인 경우에만 우선순위 큐에 담는다.
        if (blockGroup.size > 1) pq.add(blockGroup);
    }

    static boolean isValid(int r, int c, int color) {
        return r >= 0 && r < N && c >= 0 && c < N && !visited[r][c] && (color == grid[r][c] || grid[r][c] == 0);
    }
}