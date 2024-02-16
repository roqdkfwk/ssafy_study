import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(br.readLine());
			queue.add(i + 1);
		}
		
		int idx = 0;
		boolean flag = true;
		while (idx < N) {
			
			// 수열의 다음 항이 queue의 제일 앞 원소보다 크다면
			// queue의 제일 앞 원소를 stack에 add
			while (!queue.isEmpty() && seq[idx] > queue.peek()) {
				stack.add(queue.poll());
				sb.append("+\n");
			}
			
			// 수열의 다음 항의 값이 queue의 제일 앞 원소와 같다면 그 원소를 stack에 push & pop할 것이므로
			// +, -를 출력하도록 한다.
			if (!queue.isEmpty() && seq[idx] == queue.peek()) {
				queue.poll();
				sb.append("+\n-\n");
				idx++;	// 수열의 idx번 index에 해당하는 값은 출력했으므로 다음 항과 비교하기 위해 idx++
			} else if (seq[idx] == stack.peek()) {
				stack.pop();				
				sb.append("-\n");
				idx++;
			}
			
			if (idx < N && !stack.isEmpty() && seq[idx] < stack.peek())  {
				flag = false;
				break;
			}
		}
		
		if (flag) System.out.println(sb);
		else System.out.println("NO");
	}
}