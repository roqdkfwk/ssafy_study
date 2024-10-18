import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static int[][] payDay;
	static boolean[] work;
	static int maxDay;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		payDay = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			payDay[i][0] = Integer.parseInt(st.nextToken());
			payDay[i][1] = Integer.parseInt(st.nextToken());
			maxDay = Math.max(maxDay, payDay[i][1]);
		}
		
		Arrays.sort(payDay, (a, b) -> {
			return b[0] - a[0];
		});
		
		work = new boolean[maxDay + 1];
		for (int i = 0; i < N; i++) {
			int day = payDay[i][1];
			
			for (int d = day; d > 0; d--) {
				if (!work[d]) {
					work[d] = true;
					answer += payDay[i][0];
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
}