import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] queue;
	static int front = -1;
	static int rear = -1;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		queue = new int[N];
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			String method = st.nextToken();
			if (method.equals("push")) push(Integer.parseInt(st.nextToken()));
			else if (method.equals("pop")) pop();
			else if (method.equals("size")) size();
			else if (method.equals("empty")) isEmpty();
			else if (method.equals("front")) front();
			else back();
		}
		System.out.println(sb);
	}
	
	static void push(int X) {
		queue[++rear] = X;
	}
	
	static void pop() {
		if (front == rear) {
			sb.append("-1\n");
			return;
		}
		sb.append(queue[++front]).append("\n");		
	}
	
	static void size() {
		sb.append(rear - front).append("\n");
	}
	
	static void isEmpty() {
		if (front == rear) sb.append("1\n");
		else sb.append("0\n");
	}
	
	static void front() {
		if (front == rear) sb.append("-1\n");
		else sb.append(queue[front + 1]).append("\n");
	}
	
	static void back() {
		if (front == rear) sb.append("-1\n");
		else sb.append(queue[rear]).append("\n");
	}
}