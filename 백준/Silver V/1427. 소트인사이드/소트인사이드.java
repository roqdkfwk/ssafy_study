import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N = br.readLine();
		int[] sorted = new int[N.length()];
		for (int i = 0; i < N.length(); i++) sorted[i] = N.charAt(i) - '0';
		
		
		for (int i = 0; i < N.length(); i++) {
			
			int max = Integer.MIN_VALUE;
			int idx = -1;
			for (int j = i; j < N.length(); j++) {
				
				if (max < sorted[j]) {
					max = sorted[j];
					idx = j;
				}
			}
			
			sorted[idx] = sorted[i];
			sorted[i] = max;
		}
		
		for (int i = 0; i < N.length(); i++) System.out.print(sorted[i]);
	}
}