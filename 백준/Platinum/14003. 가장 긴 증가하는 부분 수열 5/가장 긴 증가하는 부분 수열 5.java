import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] A;
	static int[] idx;
	static List<Integer> list;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		idx= new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<Integer>();
		list.add(A[0]);
		idx[0] = 1;
		
		for (int i = 1; i < N; i++) {
			int left = 0;
			int right = list.size();
			
			if (A[i] > list.get(list.size() - 1)) {
				list.add(A[i]);
				idx[i] = list.size();
			} else {
				while (left < right) {
					int mid = (left + right) >> 1;
					if (A[i] > list.get(mid)) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				
				list.set(right, A[i]);
				idx[i] = right + 1;
			}
		}
		
		stack = new Stack<Integer>();
		int index = list.size();
		for (int i = N - 1; i >= 0; i--) {
			if (idx[i] == index) {
				stack.add(A[i]);
				index--;
			}
		}
		
		sb.append(list.size() + "\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		
		System.out.println(sb);
	}	// main
}