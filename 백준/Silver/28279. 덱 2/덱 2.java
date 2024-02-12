import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> deque = new LinkedList<Integer>();
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int operator = Integer.parseInt(st.nextToken());
			
			switch (operator) {
			case 1 : 
				deque.offerFirst(Integer.parseInt(st.nextToken()));
				break;
			case 2 :
				deque.offerLast(Integer.parseInt(st.nextToken()));
				break;
			case 3 :
				if (deque.isEmpty()) sb.append(-1 + "\n");
				else sb.append(deque.pollFirst() + "\n");
				break;
			case 4 :
				if (deque.isEmpty()) sb.append(-1 + "\n");
				else sb.append(deque.pollLast() + "\n");
				break;
			case 5 :
				sb.append(deque.size() + "\n");
				break;
			case 6 :
				if (deque.isEmpty()) sb.append(1 + "\n");
				else sb.append(0 + "\n");
				break;
			case 7 : 
				if (deque.size() != 0) sb.append(deque.peekFirst() + "\n");
				else sb.append(-1 + "\n");
				break;
			case 8 :
				if (deque.size() != 0) sb.append(deque.peekLast() + "\n");
				else sb.append(-1 + "\n");
				break;
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}