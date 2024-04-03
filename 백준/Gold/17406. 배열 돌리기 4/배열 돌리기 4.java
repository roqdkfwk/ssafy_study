import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;	// 행, 열의 크기
	static int K;	// 회전 연산의 개수
	static int[] seqK;	// 회전 연산 순서 저장
	static boolean[] visitK;
	static int[][] arrK;	// 회전 연산
	static int[][] A;	// 배열
	static int[][] copyA;	
	static int minSum = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		copyA = new int[R][C];
		for (int r = 0; r < R; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) A[r][c] = Integer.parseInt(st.nextToken());
		}	// A배열 완성
		
		arrK = new int[K][3];
		seqK = new int[K];
		visitK = new boolean[K];
		for (int i = 0; i < K; i++) {
			
			st = new StringTokenizer(br.readLine());
			arrK[i][0] = Integer.parseInt(st.nextToken()) - 1;	// r
			arrK[i][1] = Integer.parseInt(st.nextToken()) - 1;	// c
			arrK[i][2] = Integer.parseInt(st.nextToken());	// s
		}	// 회전 연산 저장
		
		perm(0);
		
		System.out.println(minSum);	// 최솟값 출력
	}	// main
	
	static void perm(int cnt) {
		
		if (cnt == K) {	// 연산 순서 순열을 완성했다면
			
			copyArray();
			for (int i = 0; i < K; i++) 
				rotate(arrK[seqK[i]][0], arrK[seqK[i]][1], arrK[seqK[i]][2]);	// 순서대로 r, c, s를 대입해서 배열을 회전
			
			minSum = Math.min(minSum, findMinSum());
			return;
		}
		
		for (int i = 0; i < K; i++) {
			
			if (visitK[i]) continue;	// 방문했다면 다음으로 넘어감
			
			visitK[i] = true;	// 방문하지 않았다면 방문처리
			seqK[cnt] = i;	// cnt번째로 수행할 회전을 저장
			perm(cnt + 1);
			
			visitK[i] = false;
		}
	}	// perm
	
	static void rotate(int r, int c, int s) {
		
		if (s == 0) return;
		
		int[] LU = {r - s, c - s};
		int[] LD = {r + s, c - s};
		int[] RU = {r - s, c + s};
		int[] RD = {r + s, c + s};
		// 좌상, 좌하, 우상, 우하 순서 꼭지점의 값 저장
		int[] ang = {copyA[LU[0]][LU[1]], copyA[LD[0]][LD[1]], copyA[RU[0]][RU[1]], copyA[RD[0]][RD[1]]};
		
		for (int i = RU[1]; i > LU[1]; i--) 
			copyA[RU[0]][i] = copyA[RU[0]][i - 1];
		
		for (int i = RD[0]; i > RU[0]; i--) 
			copyA[i][RD[1]] = copyA[i - 1][RD[1]];
		copyA[RU[0] + 1][RU[1]] = ang[2];
		
		for (int i = LD[1]; i < RD[1]; i++)
			copyA[LD[0]][i] = copyA[LD[0]][i + 1];
		copyA[RD[0]][RD[1] - 1] = ang[3];
		
		for (int i = LU[0]; i < LD[0]; i++)
			copyA[i][LU[1]] = copyA[i + 1][LU[1]];
		copyA[LD[0] - 1][LD[1]] = ang[1];
			
		rotate(r, c, s - 1);	// 안쪽 사각형 회전
	}	// rotate
	

	private static int findMinSum() {
		
		int minimum = Integer.MAX_VALUE;
		for (int r = 0; r < R; r++) {
			
			int sum = 0;
			for (int c = 0; c < C; c++) sum += copyA[r][c];
			
			minimum = Math.min(minimum, sum);
		}
		
		return minimum;
	}	// findMinSum

	private static void copyArray() {
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++)
				copyA[r][c] = A[r][c];	// 배열 복사
		}
	}	// copyArray
}