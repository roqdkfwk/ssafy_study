import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] numArr = new int[N];
		int[] prefixSum = new int[N];
		for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());
		prefixSum[0] = numArr[0];
		for (int i = 1; i < N; i++) prefixSum[i] = prefixSum[i - 1] + numArr[i];
		
		long sum = 0;
		if (N == 1) sum = 0;
		else {
			for (int i = 0; i < N - 1; i++) sum += numArr[i] * (prefixSum[N - 1] - prefixSum[i]);
		}
		
		System.out.println(sum);
	}
}