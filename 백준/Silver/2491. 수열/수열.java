import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		int rightLen = 0;
		int leftLen = 0;
		
		int maxLen = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i = 0; i < N - 1; i++) {
			int rightMax = 1;
			
			while (i < N - 1 && arr[i] <= arr[i + 1]) {
				rightMax++;
				i++;
			}
			if (rightLen < rightMax)
				rightLen = rightMax;
		}
		
		for (int i = N - 1; i >= 0; i--) {
			int leftMax = 1;
			
			while (i > 0 && arr[i - 1] >= arr[i]) {
				leftMax++;
				i--;
			}
			if (leftLen < leftMax)
				leftLen = leftMax;
		}
		
		if (leftLen < rightLen)
			maxLen = rightLen;
		else
			maxLen = leftLen;
		
		System.out.println(maxLen);
	}
}