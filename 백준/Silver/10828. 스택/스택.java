import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		stack = new int[N];
		for (int i = 0; i < N; i++) {
			
			// 문자열을 입력 받은 후에
			st = new StringTokenizer(br.readLine());
			String method = st.nextToken();
			// 입력받은 문자열이 "push"문자열을 포함하고 있다면
			if (method.contains("push")) {
				// push() 메소드를 호출
				push(Integer.parseInt(st.nextToken()));
			} else if (method.equals("pop")) pop();
			else if (method.equals("size")) size();
			else if (method.equals("empty")) empty();
			else top();	// method자리에 st.nextToken()쓰면 안되는 이유는??
		}
	}
	
	
	static int[] stack;
	static int size = 0;
	
	// stack에 정수를 넣는 메소드
	static void push(int X) {
		stack[size++] = X;
	}
	
//	이 메소드의 문제점은??
//	static void push(int X) {
//		size++;
//		stack = new int[size];
//		stack[size - 1] = X;
//	}
	
	// stack에서 가장 위에 있는 정수를 출력하는 메소드
	static void pop() {
		// stack내에 아무 정수도 없는 경우 -1을 출력
		if (size == 0) {
			System.out.println(-1);
			return;
		// stack내에 숫자가 있는 경우 제일 마지막 수를 출력한 후 
		} else {
			System.out.println(stack[size - 1]);
			// stack의 마지막 숫자를 0으로 바꿔준 후 size를 하나 줄여준다.
			stack[size - 1] = 0;
			size--;
		}
	}
	
	// stack내에 있는 정수의 개수를 출력하는 메소드
	static void size() {
		System.out.println(size);
	}
	
	// stack이 비어있으면 1, 아니면 0을 출력하는 메소드
	static void empty() {
		if (size == 0) System.out.println(1);
		else System.out.println(0);
	}
	
	static void top() {
		if (size == 0) System.out.println(-1);
		else {
			System.out.println(stack[size - 1]);
		}
	}
}