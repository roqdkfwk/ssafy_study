import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long N;
	static long K;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			
			st = new StringTokenizer(br.readLine());
			N = Long.parseLong(st.nextToken());
			K = Long.parseLong(st.nextToken());
			
			if (N == 0 && K == 0)
				break;
			
			ans = 1;
			
			if (K > N - K)
				K = N - K;
			
			for (long i = 1; i <= K; i++) {
				ans *= N--;
				ans /= i;
			}	// i에 대한 for문
			
			System.out.println(ans);
		}	// while
	}	// main
}