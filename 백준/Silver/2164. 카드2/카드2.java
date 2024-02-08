import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> que = new LinkedList<Integer>();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i < N + 1; i++) que.add(i);
		for (int i = 0; i < N - 1; i++) {
			que.poll();
			que.add(que.peek());
			que.poll();
		}
		System.out.println(que.peek());
	}
}