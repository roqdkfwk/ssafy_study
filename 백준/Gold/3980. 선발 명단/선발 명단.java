import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int T;
	static int[][] ability;
	static boolean[] visit;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			answer = 0;
			ability = new int[11][11];
			visit = new boolean[11];
			
			for (int r = 0; r < 11; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 11; c++) {
					ability[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int index = 0;
			combination(0, 0, 0);
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void combination(int index, int count, int sum) {
		if (count == 11) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i = 0; i < 11; i++) {
			if (visit[i]) {							// i번째 포지션에 이미 선수가 있는 경우
				continue;
			}
			if (ability[index][i] == 0) {
				continue;
			}
			visit[i] = true;						// i번째 포지션 선수 정함
			sum += ability[index][i];				// index번째 선수를 i번째 포지션에 배치
			combination(index + 1, count + 1, sum);	// 그 다음 선수를 고려
			
			visit[i] = false;
			sum -= ability[index][i];
		}
	}
}