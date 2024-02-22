import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		zigzag(r, c, N);
		System.out.println(cnt);
	}
	
	static void zigzag(int r, int c, int N) {
		
		int len = power(N);	// 행렬의 한 변의 길이 len = 2^N
		
		if (r < 0 || c < 0 || r >= len || c >= len) return;
		
		if (N == 1) cnt += 2 * r + c;	// 숫자의 시작이 0이지만 한 칸 넘어가니 - 1 + 1 = 0 상쇄
		else {
			if (r <= (len / 2) - 1 && c <= (len / 2) - 1) {	// 4등분 기준 2사분면에 위치한 경우
				zigzag(r, c, N - 1);
			} else if (r <= (len / 2) - 1 && c > (len / 2) - 1 ) {	// 4등분 기준 1사분면에 위치한 경우
				cnt += (len / 2) * (len / 2);
				zigzag(r, c - len / 2, N - 1);
			} else if (r > (len / 2) - 1 && c <= (len / 2) - 1) {	// 4등분 기준 3사분면에 위치한 경우
				cnt += 2 * (len / 2) * (len / 2);
				zigzag(r - len / 2, c, N - 1);
			} else {	// 4등분 기준 4사분면에 위치한 경우
				cnt += 3 * (len / 2) * (len / 2);
				zigzag(r - len / 2, c - len / 2, N - 1);
			}
		}
	}
	
	static int power(int N) {	// 거듭제곱 해주는 함수
		
		if (N == 0) return 1;
		else return 2 * power(N - 1);	// 재귀
	}
}