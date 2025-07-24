import java.util.*;
import java.io.*;
public class Main {
	
	/**
	 * 위, 아래 숫자가 같은 경우 무조건 포함
	 * 
	 */
	
	static int N;
	static int[][] numPair;
	static boolean[] pairNum;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		numPair = new int[2][N + 1];
		pairNum = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			numPair[1][i] = sc.nextInt();
			
			if (numPair[1][i] == i) {
				pairNum[i] = true;
				count++;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (pairNum[i]) continue;
			canMakePair(i);
		}
		
		StringBuilder answer = new StringBuilder();
		answer.append(count).append("\n");
		for (int i = 1; i <= N; i++) {
			if (pairNum[i]) {
				answer.append(i).append("\n");
			}
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static void canMakePair(int num) {
		// 시작점으로 돌아오는 경우 pair를 만들 수 있다.
		// 시작점으로 돌아오지 않거나 계속 순환하는 경우 pair를 만들 수 없다.
		
		Set<Integer> numSet = new HashSet<>();
		numSet.add(num);
		
		int prevNum = numPair[1][num];
		while (!numSet.contains(prevNum)) {
			numSet.add(prevNum);
			prevNum = numPair[1][prevNum];
		}
		
		// 시작점으로 돌아온 경우
		if (prevNum == num) {
			for (int number : numSet) {
				pairNum[number] = true;
				count++;
			}
		}
	}
}
