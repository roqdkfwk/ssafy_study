import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] deque;
	static int size = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		String method;
		deque = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			method = st.nextToken();
			
			if (method.equals("push_front")) push_front(Integer.parseInt(st.nextToken()));
			else if (method.equals("push_back")) push_back(Integer.parseInt(st.nextToken()));
			else if (method.equals("pop_front")) sb.append(pop_front()).append("\n");
			else if (method.equals("pop_back")) sb.append(pop_back()).append("\n");
			else if (method.equals("size")) sb.append(size()).append("\n");
			else if (method.equals("empty")) sb.append(isEmpty()).append("\n");
			else if (method.equals("front")) sb.append(front()).append("\n");
			else sb.append(back()).append("\n");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void push_front(int X) {
//		deque의 사이즈 1만큼 증가
		size++;
//		deque 내의 값들을 뒤로 한 칸씩 밀고
		for (int i = size - 1; i >= 0; i--) deque[i + 1] = deque[i];
//		X를 첫 번째 자리에 넣음
		deque[0] = X;
	}
	
	static void push_back(int X) {
//		deque의 제일 마지막 자리에 X를 넣은 후 사이즈를 1만큼 증가시킴
		deque[size++] = X;		
	}
	
	static int pop_front() {
		if (size == 0) return -1;
//		첫 번째 원소를 ans에 저장하고
		int ans = deque[0];
//		1번 index에 해당하는 원소부터 차례로 한 칸씩 앞으로 땡긴 후
		for (int i = 0; i < size - 1; i++) deque[i] = deque[i + 1];
//		마지막 원소를 0으로 만든 후 사이즈를 1만큼 줄이고
		deque[size--] = 0;
//		ans를 리턴한다.
		return ans;
	}
	
	static int pop_back() {
		if (size == 0) return -1;
		return deque[--size];
	}
	
	static int size() {
		return size;
	}
	
	static int isEmpty() {
		if (size == 0) return 1;
		else return 0;
	}
	
	static int front() {
		if (size == 0) return -1;
		return deque[0];
	}
	
	static int back() {
		if (size == 0) return -1;
		else return deque[size - 1];
	}
}