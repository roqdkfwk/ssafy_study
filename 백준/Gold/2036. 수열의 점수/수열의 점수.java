import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static int greaterThanOneCount, negativeCount;
	static int[] seq;
	static Deque<Integer> dq; 
	static long answer;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}
	
	static void printResult() {
		System.out.println(answer);
	}
	
	// 양수인 수들을 계산
	static long calculatePositive(int count) {
		long result = 0;
		
		for (int i = 0; i < count; i++) 
		{
			long A = dq.pollLast();
			long B = dq.pollLast();
			
			result += A * B;
		}
		
		// 남은 1을 처리하는 부분
		while(!dq.isEmpty() && dq.peekLast() > 0)
			result += dq.pollLast();
		
		return result;
	}
	
	// 음수인 수들을 계산
	static long calculateNegative(int count) {
		long result = 0;
		
		for (int i = 0; i < count; i++)
		{
			long A = dq.pollFirst();
			long B = dq.pollFirst();
			
			result += A * B;
		}
		
		// 남은 숫자가 0밖에 없는 경우
		if (dq.isEmpty() || dq.peekFirst() == 0)
		{
			return result;
		}
		// 음수가 한개 남아있는 경우
		else
		{
			int A = dq.pollFirst();
			
			if (dq.isEmpty()) return result + A;
			return result;
		}
	}
	
	static ArrayDeque<Integer> copyArrayToDeque(int[] arr) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		for (int num : arr) dq.add(num);
		
		return dq;
	}
	
	static void Solution() {
		Arrays.sort(seq);
		
		dq = copyArrayToDeque(seq);
		
		answer = calculatePositive(greaterThanOneCount / 2) + calculateNegative(negativeCount / 2); 
	}

	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		seq = new int[N];
		
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(br.readLine());
			
			if (seq[i] > 1) greaterThanOneCount++;
			if (seq[i] < 0) negativeCount++;
		}
	}
}