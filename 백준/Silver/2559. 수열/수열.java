import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] tem = new int[N];
		int[] sum = new int[N - K + 1];
		
		for (int i = 0; i < N; i++) {
			tem[i] = sc.nextInt();
		}
		
		for (int i = 0; i < N - K + 1; i++) {
			for (int j = i; j < K + i; j++) {
				sum[i] += tem[j];
			}
		}
		
		Arrays.sort(sum);
		
		System.out.println(sum[sum.length - 1]);
	}
}