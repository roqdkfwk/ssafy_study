import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] fibo = new int[N + 1];
		fibo[0] = 0;
		fibo[1] = 1;
		
		for (int i = 2; i < N + 1; i++) fibo[i] = fibo[i - 1] + fibo[i - 2];
		System.out.println(fibo[N]);
	}
}