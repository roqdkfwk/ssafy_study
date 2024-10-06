import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Tower {
		int height, index;
		
		public Tower (int height, int index) {
			this.height = height;
			this.index = index;
		}
	}
	
	static int N;
	static List<Tower> towers;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		answer = new int[N];
		
		towers = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			towers.add(new Tower(Integer.parseInt(st.nextToken()), i));
		}
		
		Stack<Tower> stack = new Stack<>();
		stack.add(towers.get(N - 1));
		for (int i = N - 2; i >= 0; i--) {
			if (!stack.isEmpty() && stack.peek().height <= towers.get(i).height) {
				while (!stack.isEmpty() && stack.peek().height <= towers.get(i).height) {
					answer[stack.peek().index - 1] = towers.get(i).index;
					stack.pop();
				}
				
				stack.add(towers.get(i));
			} else {
				stack.add(towers.get(i));
			}
		}
		
		while (!stack.isEmpty()) {
			Tower curr = stack.pop();
			answer[curr.index - 1] = 0;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}