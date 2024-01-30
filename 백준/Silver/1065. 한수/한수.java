import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		if (N < 100) {
			cnt = N;
		} else if (N < 1000){
			for (int i = 100; i <= N; i++) {
				if ((i / 100) + (i % 10) == 2 * ((i / 10) % 10)) {
					
					cnt++;
				}
			}
			cnt += 99;
		} else {
			N = 999;
			for (int i = 100; i <= N; i++) {
				if ((i / 100) + (i % 10) == 2 * ((i / 10) % 10)) {
					cnt++;
				}
			}
			cnt += 99;
		}
		System.out.println(cnt);
	}
}