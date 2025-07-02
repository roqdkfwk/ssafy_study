import java.util.*;
import java.io.*;
public class Main {
	
	static int N, M, T;
	static int[][] circle;
	static int sumOfNums;
	static int countOfNums;
	static boolean[][] isDeleted;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		isDeleted = new boolean[N + 1][M];
		circle = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			circle[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			sumOfNums += Arrays.stream(circle[i]).sum();
		}
		countOfNums = N * M;
		
		for (int i = 0; i < T; i++) {
			// 1. 원판 회전
			// operation[0] : x, operation[1] : d, operation[2] : k
			int[] operation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			rotateCircle(operation);
			
			// 2. 인접한 수 제거
			removeSameNumber();
		}
		
		System.out.println(sumOfNums);
	}
	
	private static void printGrid(boolean[][] grid) {
		System.out.println("========================");
		for (int i = 1; i <= N; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
	}
	
	private static void printGrid(int[][] grid) {
		System.out.println("========================");
		for (int i = 1; i <= N; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
	}
	
	private static void rotateCircle(int[] operation) {
		int[] tmp = new int[M];
		boolean[] tmp2 = new boolean[M];
		
		// 시계 방향 회전
		if (operation[1] == 0) {
			for (int i = operation[0]; i <= N; i += operation[0]) {
				for (int index = 0; index < M; index++) {
					tmp[(index + operation[2]) % M] = circle[i][index];
					tmp2[(index + operation[2]) % M] = isDeleted[i][index];
				}
				
				for (int j = 0; j < M; j++) {
					circle[i][j] = tmp[j];
					isDeleted[i][j] = tmp2[j];
				}
			}
		}
		// 반시계 방향 회전
		else {
			for (int i = operation[0]; i <= N; i += operation[0]) {
				for (int index = 0; index < M; index++) {
					tmp[(index - operation[2] + M) % M] = circle[i][index];
					tmp2[(index - operation[2] + M) % M] = isDeleted[i][index];
				}
				
				for (int j = 0; j < M; j++) {
					circle[i][j] = tmp[j];
					isDeleted[i][j] = tmp2[j];
				}
			}
		}
	}
	
	private static void removeSameNumber() {
		Deque<int[]> queue = new ArrayDeque<>();
		
		// 1. 좌우 인접 제거
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				// 이미 지워진 위치인 경우
				if (isDeleted[i][j % M] || isDeleted[i][(j + 1) % M]) continue;
				
				if (circle[i][j % M] == circle[i][(j + 1) % M]) {
					queue.add(new int[] {i, j % M});
					queue.add(new int[] {i, (j + 1) % M});
				}
			}
		}
		
		// 2. 상하 인접 제거
		for (int j = 0; j < M; j++) {
			for (int i = 1; i < N; i++) {
				// 이미 지워진 위치인 경우
				if (isDeleted[i][j] || isDeleted[i + 1][j]) continue;
				
				if (circle[i][j] == circle[i + 1][j]) {
					queue.add(new int[] {i, j});
					queue.add(new int[] {i + 1, j});
				}
			}
		}
		
		// 인접해있는 같은 수가 없는 경우
		if (queue.isEmpty()) {
			double mean = sumOfNums / (double)countOfNums;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					// 이미 지워진 위치인 경우
					if (isDeleted[i][j]) continue;
					
					if (circle[i][j] < mean) {
						circle[i][j]++;
						sumOfNums++;
					} else if (circle[i][j] > mean) {
						circle[i][j]--;
						sumOfNums--;
					}
				}
			}
		}
		// 인접해있는 같은 수가 있는 경우
		else {
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				
				if (isDeleted[curr[0]][curr[1]]) continue;
				
				isDeleted[curr[0]][curr[1]] = true;
				sumOfNums -= circle[curr[0]][curr[1]];
				countOfNums--;
			}
		}
	}
}