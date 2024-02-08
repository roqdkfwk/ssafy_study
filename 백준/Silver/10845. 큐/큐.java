import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] queue;
	static int size = 0;
	static int front = -1;
	static int rear = -1;
	
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
		
	}
	
	static void push(int X) {
		queue[++rear] = X;
	}
	
	static int pop() {
		if (front == rear) {
			System.out.println(-1);
			return -1;
		}
		int num = queue[++front];
		queue[front] = 0;
		System.out.println(num);
		return num;
	}
	
	static void size() {
		System.out.println(rear - front);
	}
	
	static void isEmpty() {
		if (front == rear) {
			System.out.println(1);
		}
		else {System.out.println(0);
		}
	}
	
	static void front() {
		if (front == rear) {
			System.out.println(-1);
			return;
		}
		System.out.println(queue[front + 1]);
	}
	
	static void back() {
		if (front == rear) {
			System.out.println(-1);
			return;
		}
		System.out.println(queue[rear]);
	}
}