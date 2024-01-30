import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			int[] cntArr = new int[101];
			int N = sc.nextInt();
			for (int j = 0; j < 1000; j++) {
				int score = sc.nextInt();
				cntArr[score]++;
			}
			
			int maxIdx = 0;
			int max = 0;
			
			for (int k = 0; k < 101; k++) {
				if (cntArr[k] >= max) {
					max = cntArr[k];
					maxIdx = k;
				}
			}
			System.out.println("#" + N + " " + maxIdx);
		}
	}
}