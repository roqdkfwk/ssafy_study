import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int S;	// 샌드위치 가격
	static int M;	// 쿠기가 가지고 있는 금액
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		queue = new LinkedList<>();
		for (int i = 9; i >= 0; i--) {
			
			if ((1 << i) <= M) {
				M -= (1 << i);
				queue.offer((1 << i));
			}
		}
		
		for (int i = 9; i >= 0; i--) {
			
			if ((1 << i) <= S) S -= (1 << i);
		}
		
		if (S == 0) System.out.println("No thanks");
		else {
			while (S != 0 && !queue.isEmpty()) {
				if (queue.peek() <= S) {
					S -= queue.poll();
				} else {
					queue.poll();
				}
			}
			if (S == 0) System.out.println("Thanks");
			else System.out.println("Impossible");
		}
	}	// main
}