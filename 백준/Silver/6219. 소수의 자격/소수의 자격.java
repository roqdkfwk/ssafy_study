import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int A, B, D;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		ans = 0;
		
		for (int i = A; i <= B; i++) {
			
			if (isPrime(i)) {
				
				int tmp = i;
				while (tmp != 0) {
					
					if (tmp % 10 == D) {
						ans++;
						break;
					}
					
					tmp /= 10;
				}	// while
			}	// if
		}
		
		System.out.println(ans);
	}	// main

	private static boolean isPrime(int num) {

		if (num == 1) 
			return false;
		
		for (int i = 2; i <= Math.sqrt(num); i++) {
			
			if (num % i == 0)
				return false;
		}
		
		return true;
	}	// isPrime
}