import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	
	static int T;
	static int N;
	static boolean primeFlag;
	static ArrayList<Integer> primeNumber;
	static int count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			primeNumber = new ArrayList<Integer>();
			
			for (int i = 2; i <= N; i++) {
				
				primeFlag = true;
				for (int j = 2; j <= Math.sqrt(i); j++) {
					if (i % j == 0) {	// i를 j로 나눴을 때 나머지가 0이면 합성수이므로 반복문을 종료하고 다음 소수를 찾는다.
						primeFlag = false;
						break;
					}
				}	// j에 대한 for문
				if (primeFlag) primeNumber.add(i);	// i는 소수이므로 배열에 추가
			}	// i에 대한 for문
			
			count = 0;
			for (int i = 0; i < primeNumber.size(); i++) {
				for (int j = i; j < primeNumber.size(); j++) {
					
					if (primeNumber.get(i) + primeNumber.get(j) >= N) break;	// 두 소수의 합이 N보다 크거나 같다면 세 번째 소수를 체크하지 않고 바로 다음 반복문으로 넘어감
					for (int k = j; k < primeNumber.size(); k++) {
						if (primeNumber.get(i) + primeNumber.get(j) + primeNumber.get(k) == N) count++;	// 세 소수의 합이 N과 같다면 count를 1만큼 증가시켜줌
					}
				}
			}
			
			System.out.println("#" + t + " " + count);
		}	// t에 대한 for문
	}
}