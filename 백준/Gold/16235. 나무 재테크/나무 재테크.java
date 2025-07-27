import java.util.*;
import java.io.*;
public class Main {
	
	static class Tree implements Comparable<Tree> {
		int r, c, age;
		
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree t) {
			return Integer.compare(this.age, t.age);
		}
	}
	
	static int N, M, K;
	static int[][] land, A;
	static PriorityQueue<Tree>[][] trees;
	static Deque<Tree> deadTrees;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		land = new int[N][N];
		A = new int[N][N];
		trees = new PriorityQueue[N][N];
		deadTrees = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(land[i], 5);
		}
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				trees[r][c] = new PriorityQueue<>();
			}
		}
		for (int i = 0; i < M; i++) {
			int[] tree = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			trees[tree[0] - 1][tree[1] - 1].add(new Tree(tree[0] - 1, tree[1] - 1, tree[2]));
		}
		
		for (int k = 0; k < K; k++) {
			spring();
			
			summer();
			
			fall();
			
			winter();
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				answer += trees[r][c].size();
			}
		}
		
		System.out.println(answer);
	}

	/**
	 * 봄에는 나이만큼 양분 흡수, 나이 1 증가
	 * 하나의 칸에 여러 그루의 나무가 있는 경우 나이가 어린 나무부터 흡수
	 * >> 나이가 어린 나무부터 양분을 흡수하게 만들기 위해서 우선순위 큐 사용
	 * >> 양분을 흡수한 나무가 또 양분을 흡수하는 경우를 방지하기 위해 임시 큐에 넣는다
	 * 양분을 흡수하지 못한 나무는 즉시 죽는다
	 * >> 양분을 흡수하지 못한 죽은 나무들을 큐에 넣음
	 */
	private static void spring() {
		Deque<Tree> tmp = new ArrayDeque<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				while (!trees[r][c].isEmpty()) {
					// 가장 나이가 어린 나무부터
					Tree tree = trees[r][c].poll();
					
					// 나이 <= 양분
					if (tree.age <= land[r][c]) {
						land[r][c] -= tree.age;
						tmp.add(new Tree(r, c, tree.age + 1));
					}
					// 나이 > 양분
					else {
						deadTrees.add(tree);
					}
				}
				
				// 나이를 먹은 나무들을 원상복구
				for (Tree tree : tmp) {
					trees[r][c].add(tree);
				}
				tmp.clear();
			}
		}
	}
	
	/**
	 * 여름에는 죽은 나무가 양분으로 변한다
	 * >> 죽은 나무들의 위치를 알기 위해서 Tree 클래스에 좌표 r, c 필요
	 */
	private static void summer() {
		while (!deadTrees.isEmpty()) {
			Tree tree = deadTrees.pollFirst();
			land[tree.r][tree.c] += (tree.age / 2);
		}
	}
	
	/**
	 * 가을에는 나이가 5의 배수인 나무가 번식
	 */
	private static void fall() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (Tree tree : trees[r][c]) {
					// 나무의 나이가 5의 배수가 아닌 경우
					if (tree.age % 5 != 0) continue;
					
					// 나무의 나이가 5의 배수인 경우 8방향으로 나무가 번식
					for (int i = 0; i < 8; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if (!isInside(nr, nc)) continue;
						trees[nr][nc].add(new Tree(nr, nc, 1));
					}
				}
			}
		}
	}
	
	private static boolean isInside(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	/**
	 * 겨울에는 양분을 추가
	 */
	private static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				land[r][c] += A[r][c];
			}
		}
	}
}