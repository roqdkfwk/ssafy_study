import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 소수 찾기
		boolean[] prime = new boolean[N + 1]; 
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for (int i = 2; i * i <= N; i++) {
			if (prime[i]) {
				for (int j = i * i; j <= N; j += i) {
					prime[j] = false;
				}
			}
		}
		
		// 소수들을 set에 저장
		int cntPrime = 0;
		Set<Integer> primeSet = new HashSet<Integer>();
		for (int i = 0; i <= N; i++) {
			if (prime[i]) {
				primeSet.add(i);
				cntPrime++;
			}
		}
		
		// 소수들의 합 prefix 만들기
		Integer[] primeArr = primeSet.toArray(new Integer[0]);
		Arrays.sort(primeArr);
		
		int[] prefix = new int[cntPrime + 1];
		prefix[0] = 0;
		for (int i = 1; i <= cntPrime; i++) {
			prefix[i] = prefix[i - 1] + primeArr[i - 1];
		}
		
		// 투 포인터로 해당 값을 찾을 수 있는지 탐색
		int answer = 0;
		int left = 0, right = 0;

		while (right <= cntPrime) {
			int sum = prefix[right] - prefix[left];
			
			if (sum > N) {
				left++;
			} else if (sum < N) {
				right++;
			} else {
				answer++;
				left++;
			}
		}
		
		System.out.println(answer);
	}
}