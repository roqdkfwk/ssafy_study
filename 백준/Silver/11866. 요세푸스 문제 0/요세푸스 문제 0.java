import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		
		sb.append("<");
		
		int N = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N + 1; i++) queue.offer(i);
			
		int K = Integer.parseInt(st.nextToken());
		while (!queue.isEmpty()) {
			// 제일 앞의 원소를 제일 뒤로 보낸 후
			for (int i = 0; i < K - 1; i++) queue.offer(queue.poll());
			sb.append(queue.poll()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb);
	}
}