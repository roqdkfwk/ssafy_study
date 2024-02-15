import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) queue.add(Integer.parseInt(st.nextToken()));
		
		// queue의 제일 앞에 1이 위치할때까지 stack으로 poll
		while (queue.peek() != 1) stack.add(queue.poll());
		// queue에서 1을 꺼냄
		queue.poll();
		
		int num = 1;	// 이전에 간식을 받은 사람의 순서
		boolean flag = true;
		while (flag) {
			
			flag = false;	// 순서대로 간식을 받지 못하는 경우 whild문이 반복되지 않도록 해줌
			num++;	// 다음에 간식을 받을 사람의 순서
			// queue에서 기다리는 사람이 다음 순서로 간식을 받을 사람이면
			if (!queue.isEmpty() && queue.peek() == num) {
				// 꺼내고
				queue.poll();
				// flag를 true로 만들어서 while문이 계속 반복되도록 해줌
				flag = true;
			// stack에서 기다리는 사람이 다음 순서로 간식을 받을 사람이면
			} else if (!stack.isEmpty() && stack.peek() == num) {
				// 꺼내고
				stack.pop();
				// flag를 true로 만들어서 while문이 계속 반복되도록 해줌
				flag = true;
			// queue가 비어있지 않지만 기다리는 사람이 다음 순서로 간식을 받을 사람이 아니라면
			} else if (!queue.isEmpty() && queue.peek() != num) {
				stack.add(queue.poll());
				num--;
				flag = true;
			} else if (queue.isEmpty() && stack.isEmpty()) {
				flag = true;
				break;
			} else break;
		}
		
		if (queue.isEmpty() && stack.isEmpty()) System.out.println("Nice");
		else System.out.println("Sad");			
	}
}