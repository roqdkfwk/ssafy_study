import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int left = 0;
		int right = N - 1;
		int sum = Integer.MAX_VALUE;
		String answer = "";
		while (left < right) {
			if (arr[left] + arr[right] == 0) {
				System.out.println(arr[left] + " " + arr[right]);
				return;
			}
			
			if (sum > Math.abs(arr[left] + arr[right])) {
				sum = Math.abs(arr[left] + arr[right]);
				answer = arr[left] + " " + arr[right];
			}
			
			if (arr[left] + arr[right] < 0) {
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(answer);
	}
}