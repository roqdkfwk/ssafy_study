import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) queue.add(i + 1);
		
		for (int i = 0; i < N - 1; i++) {
			sb.append(queue.poll()).append(" ");
			queue.add(queue.poll());
		}
		sb.append(queue.poll());
		
		System.out.println(sb);
	}
}