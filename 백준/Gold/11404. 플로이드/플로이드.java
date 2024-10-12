import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] city;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		city = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(city[i], INF);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			
			city[A][B] = Math.min(city[A][B], C);
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					} else {
						city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
					}
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (city[r][c] == INF) {
					city[r][c] = 0;
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(city[r][c] + " ");
			}
			System.out.println();
		}
	}
}