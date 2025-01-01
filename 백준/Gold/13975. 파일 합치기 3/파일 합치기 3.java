import java.util.*;
import java.io.*;
public class Main {

	static int T;
	static int K;
	static PriorityQueue<Long>[] files;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
	}
	
	static void Solution() {
		for (int t = 0; t < T; t++) {
			answer = 0;
			
			int size = files[t].size();
			for (int i = 0; i < size - 1; i++) {
				
				long sum = files[t].poll() + files[t].poll();
				answer += sum;
				files[t].add(sum);
			}
			
			System.out.println(answer);
		}
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		files = new PriorityQueue[T];
		for (int i = 0; i < T; i++)
			files[i] = new PriorityQueue<>();
		
		for (int i = 0; i < T; i++) {
			K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++)
				files[i].add(Long.parseLong(st.nextToken()));
		}
	}
}