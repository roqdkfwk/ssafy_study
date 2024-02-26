import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] comb = new int[30][30];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == i || j == 0) comb[i][j] = 1;
				else comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());	// M개 중 N개 선택할 것
			
		
			System.out.println(comb[M][N]);
		}
	}
}