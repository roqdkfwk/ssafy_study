import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static int[] nums;
	static int answer = 100_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		if (N == 1) {
			System.out.println(0);
			return;
		}
		
		operation(nums[0] - nums[1], nums[1], 2, 0);					// 0, 0
		operation(nums[0] - (nums[1] - 1), (nums[1] - 1), 2, 1);		// 0, -1
		operation((nums[0] - 1) - nums[1], nums[1], 2, 1);				// -1, 0
		operation((nums[0] - 1) - (nums[1] - 1), nums[1] - 1, 2, 2);	// -1, -1
		operation(nums[0] - (nums[1] + 1), nums[1] + 1, 2, 1);			// 0, 1
		operation((nums[0] + 1) - nums[1], nums[1], 2, 1);				// 1, 0
		operation((nums[0] + 1) - (nums[1] + 1), nums[1] + 1, 2, 2);	// 1, 1
		operation((nums[0] - 1) - (nums[1] + 1), nums[1] + 1, 2, 2);	// -1, 1
		operation((nums[0] + 1) - (nums[1] - 1), nums[1] - 1, 2, 2);	// 1, -1
		
		System.out.println(answer == 100_000 ? -1 : answer);
	}
	
	private static void operation(int difference, int currNum, int index, int count) {
		// 수열의 마지막 항까지 조사했는데 등차수열을 만족하는 경우
		if (index == N) {
			answer = Math.min(answer, count);
			return;
		}
		
		// 연산은 하지 않았을 때, +1했을 때, -1했을 때 등차수열을 만족하는 경우에만 다음항을 조사한다.
		if (difference == (currNum - nums[index])) {
			operation(difference, nums[index], index + 1, count);
			return;
		}
		
		if (difference == (currNum - (nums[index] - 1))) {
			operation(difference, nums[index] - 1, index + 1, count + 1);
			return;
		}
		
		if (difference == (currNum - (nums[index] + 1))) {
			operation(difference, nums[index] + 1, index + 1, count + 1);
			return;
		}
	}
}
