import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger[] fibo = new BigInteger[10001];
		fibo[0] = BigInteger.ZERO;
		fibo[1] = BigInteger.ONE;
		fibo[2] = BigInteger.ONE;
		for (int i = 3; i < 10001; i++) fibo[i] = fibo[i - 1].add(fibo[i - 2]);
		
		int N = Integer.parseInt(br.readLine());
		System.out.println(fibo[N]);
	}
}