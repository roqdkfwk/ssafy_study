import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		// 블로그를 시작하고 지난 일수
		int N = Integer.parseInt(st.nextToken());
		// 기간 일수
		int X = Integer.parseInt(st.nextToken());
		
		int start = 0;
		
		// 일별 방문자를 저장하는 배열 완성
		int[] visit = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			visit[i] = Integer.parseInt(st.nextToken());
		}
		
		// 누적합 배열 완성
		int[] stackSum = new int[N];
		stackSum[0] = visit[0];
		for (int i = 1; i < N; i++) {
			stackSum[i] = stackSum[i - 1] + visit[i];
		}

		// X일 동안 가장 많이 온 방문자의 수  
		int max = stackSum[start + X - 1];
		for (start = 0; start < N - X; start++) {
			if (stackSum[start + X] - stackSum[start] > max) {
				max = stackSum[start + X] - stackSum[start];
			}
		}
		
		// X일 동안 방문한 방문자의 수의 합과
		// X일 동안 가장 많이 온 방문자의 수의 합이 같은 횟수
		int cnt = 0;
		
		// 첫 날부터 X째 날까지의 합이 max와 같은 경우는 따로 처리
		if (stackSum[X - 1] == max)
			cnt++;
		for (start = 0; start < N - X; start++) {
			if (stackSum[start + X] - stackSum[start] == max) {
				cnt++;
			}
		}
		if (max == 0) {
			System.out.println("SAD");
		} else {
			System.out.printf("%d\n%d", max, cnt);
		}
	}
}