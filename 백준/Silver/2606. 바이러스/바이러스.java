import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int map[][];
	static boolean visit[];
	static int count = 0;

	public static int search(int i) {
		
		visit[i] = true;

		for (int n = 1; n <= N; n++) {
			
			if (map[i][n] == 1 && visit[n] == false) {
				count++;
				search(n);
			}
		}
		
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1]; // 각 정점간 탐색 경로를 저장할 배열
		visit = new boolean[N + 1]; // 정점의 탐색 여부 체크

		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = map[c][r] = 1;
		}

		System.out.println(search(1));
	}
}