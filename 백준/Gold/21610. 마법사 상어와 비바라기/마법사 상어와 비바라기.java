import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] grid;
	static boolean[][] cloud1, cloud2;
	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 좌 좌상 상 우상 우 우하 하 좌하

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new int[N][N];
		cloud1 = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = N - 2; r < N; r++) {
			for (int c = 0; c < 2; c++) {
				cloud1[r][c] = true;
			}
		}

		// s가 N의 범위를 벗어나는 엣지 케이스 발생할 수 있다.
		// N이 10일 때 s는 3이나 13이나 같다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1; // 움직이는 방향
			int s = Integer.parseInt(st.nextToken()) % N; // 움직이는 칸

			moveCloud(d, s);
		}
		
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				answer += grid[r][c];
			}
		}
		
		System.out.println(answer);
	}

	// 구름이 이동
	// 구름이 있는 칸의 물의 양이 1 증가한다.
	// 물복사 마법 시전
	// 구름이 있던 위치에 물의 양을 증가시킨다.
	// 구름이 있던 칸을 제외하고 나머지 칸에서 구름을 생성하고 물의 양을 2만큼 줄인다.
	// 구름이 있던 칸은 구름을 생성하면 안댐.

	// 구름은 어떻게 처리하지
	// 구름 배열을 2개를 쓴다 or 구름이 있던 위치를 저장해놓고 구름을 생성하고 있던 구름을 제거한다

	// 이동 방향, 이동 횟수
	private static void moveCloud(int d, int s) {
		Queue<int[]> queue = new LinkedList<>();

		cloud2 = new boolean[N][N];

		// 구름 이동
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (cloud1[r][c]) { // cloud1에 구름이 있다면
					queue.add(new int[] { r, c });
					cloud1[r][c] = false;
				}
			}
		}

		// cloud1에서 구름이 위치한 부분을 제외하고 cloud2에 구름 생성
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			curr[0] = (curr[0] + (dr[d] * s) + N) % N;	// d방향으로 s칸 이동, 마이너스가 되는 경우를 피하기 위해서 N만큼 더해준다.
			curr[1] = (curr[1] + (dc[d] * s) + N) % N;

			cloud2[curr[0]][curr[1]] = true;			// 이동한 위치에 구름을 생성하고
			grid[curr[0]][curr[1]]++;					// 해당 위치에 물의 양을 1만큼 증가시킨다.
		}

		copyWater(cloud2); // 대각선 방향에 물이 있는 곳을 체크하고 물의 양을 증가

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!cloud2[r][c] && grid[r][c] >= 2) {
					grid[r][c] -= 2;
					cloud1[r][c] = true; // 다시 구름 생성
				}
			}
		}
	}

	// 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 바구니의 물의 양이 증가한다.
	private static void copyWater(boolean[][] cloud) {
		Queue<int[]> queue = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (cloud[r][c]) {					// 구름이 있던 자리이면
					queue.add(new int[] { r, c });	// 큐에 추가한다.
				}
			}
		}

		int[][] copyGrid = new int[N][N]; // grid를 체크하면 없던 물이 생길 수 있으니 복사 배열 생성
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copyGrid[r][c] = grid[r][c];
			}
		}

		while (!queue.isEmpty()) {
			int[] curr = queue.poll(); // 구름이 있던 위치를 기준으로
			for (int d = 1; d < 8; d += 2) { // 대각선 방향만 고려해서
				int NR = curr[0] + dr[d];
				int NC = curr[1] + dc[d];

				if (isOk(NR, NC) && copyGrid[NR][NC] != 0) {	// 범위 내, 물이 있다면
					grid[curr[0]][curr[1]]++;					// 물의 양을 1만큼 증가한다.
				}
			}
		}
	}

	private static boolean isOk(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}