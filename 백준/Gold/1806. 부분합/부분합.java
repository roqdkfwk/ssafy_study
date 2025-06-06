import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		long[] prefix = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num >= S) {
				System.out.println(1);
				return;
			}
			
			prefix[i] = prefix[i - 1] + num;
		}
		
		int left = 0;
		int right = 1;
		int answer = Integer.MAX_VALUE;
		while(right <= N) {
			long sum = prefix[right] - prefix[left];
			
			if (sum >= S) {
				answer = Math.min(answer, right - left);
				left++;
			} else {
				right++;
			}
		}
		
		if (answer == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(answer);
	}
}