import java.util.*;
import java.io.*;
public class Main {
	
	static int T;
	static char[] operator;
	static String[] array;
	static ArrayDeque<String> dq;
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			operator = br.readLine().toCharArray();

			br.readLine();
			
			String input = br.readLine();
			if (input.equals("[]")) {
				array = new String[0];
			} else {
				array = input.substring(1, input.length() - 1).split(",");
			}
			
			dq = new ArrayDeque<>(Arrays.asList(array));

			int countR = operate(operator);
			
			sb = new StringBuilder();
			sb.append("[");
			
			int size = dq.size();
			if (countR == -1) {
				System.out.println("error");
			} else if (size == 0) {
				System.out.println("[]");
			} else if (countR % 2 == 0) {
				for (int i = 0; i < size; i++) sb.append(dq.pollFirst()).append(",");
				sb.deleteCharAt(sb.length() - 1);
				sb.append("]");
				
				System.out.println(sb);
			} else {
				for (int i = 0; i < size; i++) sb.append(dq.pollLast()).append(",");
				sb.deleteCharAt(sb.length() - 1);
				sb.append("]");
				
				System.out.println(sb);
			}
		}
	}
	
	static int operate(char[] ch) {
		int countR = 0;
		
		for (char op : ch) {
			if (op == 'R') countR++;
			
			if (op == 'D') {
				if (dq.isEmpty())
					return -1;
				
				if (countR % 2 == 0) dq.pollFirst();
				else dq.pollLast();
			}
		}
		
		return countR;
	}
}