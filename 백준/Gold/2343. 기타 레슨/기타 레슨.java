import java.util.*;
import java.io.*;
public class Main {
	
	/**
	 * N: 강의의 개수, M: 블루레이의 개수
	 * M개의 블루레이의 크기는 모두 동일하면서, 크기를 최소로
	 */
	static int N, M;
	static int[] blues;
	static int answer;
	public static void main(String[] args) throws IOException {
		inputHandler();
		
		solution();
		
		printResult();
	}
	
	private static void inputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = tmp[0];
		M = tmp[1];
		blues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	
	/**
	 * 블루레이의 크기를 결정
	 * 이분탐색으로 M개 내로 모두 녹화 가능한지 체크
	 * 최솟값 찾는 이분탐색
	 */
	private static void solution() {
		int left = 0;
		int right = 0;
		for (int length : blues) {
			left = Math.max(left, length);
			right += length;
		}

		int mid = 0;
		while (left < right) {
			mid = (left + right) >> 1;
			
			if (binarySearch(mid) > M) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		answer = left;
	}
	
	private static void printResult() {
		System.out.println(answer);
	}
	
	private static int binarySearch(int length) {
		int requiredBlueray = 1;		
		int lengthSum = 0;
		for (int i = 0; i < N; i++) {
			if (lengthSum + blues[i] > length) {
				requiredBlueray++;
				if (requiredBlueray > M) {
					return M + 1;
				}
				
				lengthSum = blues[i];
				continue;
			}
			
			lengthSum += blues[i];
		}
		
		return requiredBlueray;
	}
}