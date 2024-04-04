import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int T;	// 테스트 케이스의 수
	static int N;	// 거스름돈
	static int[] coin = {50000, 10000, 5000, 1000, 500, 100, 50, 10}; // 거스름돈의 종류
	static int[] used;	// 사용한 거스름돈의 종류별 개수를 저장할 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + "\n");
			
			N = Integer.parseInt(br.readLine());

			used = new int[8];
			
			for (int i = 0; i < 8; i++) {
				
				if (N >= coin[i]) {
					int Q = (N / coin[i]);
					N %= coin[i];
					used[i] = Q;
				}
			}
			
			for (int i = 0; i < 8; i++)
				sb.append(used[i] + " ");
			sb.append("\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}	// main
}