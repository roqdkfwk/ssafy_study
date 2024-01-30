import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numArr = new int[N];
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - 1 - i; j++) {
				if (numArr[j] > numArr[j + 1]) {
					int tmp = numArr[j];
					numArr[j] = numArr[j + 1];
					numArr[j + 1] = tmp;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(numArr[i]);
		}
	}
}