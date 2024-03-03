import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;	// 테스트케이스의 개수
	static int[][] tmehzn;
	static boolean[] visit = new boolean[9];	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			sb.append("#" + t + " ");
			
			tmehzn = new int[9][9];
			for (int r = 0; r < 9; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 9; c++) tmehzn[r][c] = Integer.parseInt(st.nextToken());
			}
			
			boolean flag = true;
			
			for (int r = 0; r < 9; r++) {
				
				if (flag == false) break;
				
				for (int i = 0; i < 9; i++) visit[i] = false;
				for (int c = 0; c < 9; c++) {
					visit[tmehzn[r][c] - 1] = true;
				}
				for (int i = 0; i < 9; i++) {
					if (visit[i] == false) flag = false;
				}
			}
			
			for (int c = 0; c < 9; c++) {
				
				if (flag == false) break;
				for (int i = 0; i < 9; i++) visit[i] = false;
				for (int r = 0; r < 9; r++) {
					visit[tmehzn[r][c] - 1] = true;
				}
				for (int i = 0; i < 9; i++) {
					if (visit[i] == false) flag = false;
				}
				
			}
			
			for (int i = 0; i < 9; i += 3) {
				
				if (flag == false) break;
				for (int idx = 0; idx < 9; idx++) visit[idx] = false;
				for (int r = 0 + i; r < 3 + i; r++) {
					
					if (flag == false) break;
					for (int c = 0 + i; c < 3 + i; c++) {
						visit[tmehzn[r][c] - 1] = true;
					}
				}
				for (int idx = 0; idx < 9; idx++) {
					if (visit[i] == false) flag = false;
				}
			}
			
			if (flag == true) sb.append(1 + "\n");
			else sb.append(0 + "\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}
}