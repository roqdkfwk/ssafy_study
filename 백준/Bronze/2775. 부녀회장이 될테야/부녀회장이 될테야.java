import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			int floor = Integer.parseInt(br.readLine());	// 계약할 집의 층
			int room = Integer.parseInt(br.readLine());		// 계약할 집의 호수
			int[][] stackSum = new int[floor + 1][room + 1];
			
			for (int c = 0; c < room + 1; c++) stackSum[0][c] = c;	// 0층의 호수별 거주 인원
			
			for (int r = 0; r < floor; r++) {
				for (int c = 0; c < room; c++) {
					stackSum[r + 1][c + 1] = stackSum[r + 1][c] + stackSum[r][c + 1];
				}
			}
			
			sb.append(stackSum[floor][room] + "\n");
		}
		System.out.println(sb);
	}
}