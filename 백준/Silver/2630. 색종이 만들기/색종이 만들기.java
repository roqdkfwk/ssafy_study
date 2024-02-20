import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] paper;	// 종이
	static int[] color;		// 흰색, 파란색 색종이의 개수를 저장할 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		
		color = new int[2];
		
		// 종이에 숫자 써넣기 
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		cutPaper(0, 0, N);
		
		System.out.printf("%d\n%d", color[0], color[1]);
	}
	
	// 종이에 써진 숫자들이 모두 같은지 확인할 메소드
	static boolean checkPaper(int r, int c, int len) {	// len은 체크하고자하는 종이의 한 변의 길이
		
		int num = paper[r][c];	// 종이에 쓰여있는 첫 번째 숫자
		
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				// r행 c열에 써있는 숫자가 종이에 써있는 첫 번째 숫자와 같지 않다면 false를 반환하고 메소드를 종료
				if (paper[i][j] != num)	return false;
			}
		}
		
		return true;
	}
	
	static void cutPaper(int r, int c, int N) {
		
		// checkPaper(r, c, N) == true인 경우(=모든 색깔이 같은 경우)
		// 흰색이면 color배열의 0번 index, 파란색이면 color배열의 1번 index의 값을 1만큼 증가
		if (checkPaper(r, c, N)) color[paper[r][c]]++;
		// checkPaper(r, c, N) == false이면
		// 종이를 4개로 잘라서 각각 다시 검사
		else {
			int len = N / 2;	// 검사하고자 하는 종이의 한 변의 길이
			// 종이를 4개로 잘라서 각각의 종이에 대해 다시 검사를 진행
			cutPaper(r, c, len);
			cutPaper(r + len, c, len);
			cutPaper(r, c + len, len);
			cutPaper(r + len, c + len, len);
		}
	}
}