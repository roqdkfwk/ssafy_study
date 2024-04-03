import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 개수
	static int N;	// 한 변의 길이
	static int[][] A;	// 방
	static boolean[][] visit;	// 방문 체크
	static Stack<int[]> stack;
	static int[] dr = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static int minNum;
	static int maxLen;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + " ");
			
			// 테스트 케이스마다 maxNum과 maxLen을 초기화
			minNum = Integer.MAX_VALUE;	
			maxLen = Integer.MIN_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			A = new int[N][N];
			for (int r = 0; r < N; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) A[r][c] = Integer.parseInt(st.nextToken());
			}	// A배열 완성
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					DFS(r, c);				
				}
			}	// DFS 탐색할 for문
			
			sb.append(minNum + " " + maxLen).append("\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}	// main
	
	static void DFS(int row, int col) {
		
		stack = new Stack<>();
		stack.add(new int[] {row, col});
		
		int dist = 1;
		
		while (!stack.isEmpty()) {
			
			int[] now = stack.pop();
			int rNow = now[0];
			int cNow = now[1];
			
//			System.out.println("rNow : " + rNow + " cNow : " + cNow);
			for (int i = 0; i < 4; i++) {
				
//				System.out.println("i : " + i);
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				
//				System.out.println("rNext : " + rNext + " cNext : " + cNext);
				
				if (rNext < 0 || rNext >= N || cNext < 0 || cNext >= N) {	// 배열의 범위를 벗어나는 경우
//					System.out.println("continue\n");
					continue;
				}
				
//				// 다음 위치에 적힌 숫자가 현재 위치에 적힌 숫자보다 1만큼 작은 경우
//				// 최댓값이 나올 수 없으므로 메소드를 종료
//				if (A[rNow][cNow] - 1 == A[rNext][cNext]) {
//					System.out.println("return\n");
//					return;
//				}
				
				// 다음 위치에 적힌 숫자가 현재 위치에 적힌 숫자보다 1만큼 큰 경우
				// 해당 위치를 stack에 저장하고 이동 거리를 1만큼 증가시킴
//				System.out.println("rNow : " + rNow + " cNow : " + cNow);
//				System.out.println("rNext : " + rNext + " cNext : " + cNext);
//				System.out.println("A[rNow][cNow] : " + A[rNow][cNow] + " A[rNext][cNext] : " + A[rNext][cNext]);
				if (A[rNow][cNow] + 1 == A[rNext][cNext]) {
//					System.out.println("i : " + i + " row : " + row + " col : " + col + "\n");
					stack.add(new int[] {rNext, cNext});
					dist++;
				}
			}	// 4방탐색 for문
		}	// while
		
		if (maxLen < dist) {	// 이동 거리가 최대인 경우
			maxLen = dist;	// 최대 이동 거리를 갱신하고
			minNum = A[row][col];	// 이동 거리가 최대가 되도록 하는 시작점의 번호를 갱신함
		} else if (maxLen == dist) {
			minNum = Math.min(A[row][col], minNum);
		}
	}	// DFS
}