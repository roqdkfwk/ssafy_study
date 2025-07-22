import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] sortedArr = new int[N];
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		sortedArr = Arrays.copyOf(arr, N);
		Arrays.sort(sortedArr);
		
		/**
		 * 인접한 숫자와 상대 순서가 1만큼 차이나는 경우는 오름차순 or 내림차순으로 정렬되어 있는 상태이므로
		 * 인접한 숫자와 상대 순서가 2 이상 차이나는 경우에만 가위질을 한다.
		 * 
		 * 숫자의 상대 순서를 저장하고 빠르게 조회하는 방법
		 */
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			// <숫자, 순서> 쌍
			map.put(sortedArr[i], i);
		}
		
		int answer = 0;
		int prevIndex = map.get(arr[0]);
		for (int i = 1; i < N; i++) {
			int currIndex = map.get(arr[i]);
			if (Math.abs(prevIndex - currIndex) != 1) answer++;
			
			prevIndex = currIndex;
		}
		
		System.out.println(answer);
	}
}
