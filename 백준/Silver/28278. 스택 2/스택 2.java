import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Stack<Integer> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int O = Integer.parseInt(st.nextToken());

			switch (O) {
			case 1:
				int X = Integer.parseInt(st.nextToken());
				stack.add(X);
				break;
			case 2:
				if (!stack.isEmpty()) {
					sb.append(stack.pop()).append("\n");
//					System.out.println(stack.pop());
				} else {
					sb.append(-1).append("\n");
//					System.out.println(-1);
				}
				break;
			case 3:
				sb.append(stack.size()).append("\n");
//				System.out.println(stack.size());
				break;
			case 4:
				if (stack.isEmpty()) {
					sb.append(1).append("\n");
//					System.out.println(1);
				} else {
					sb.append(0).append("\n");
//					System.out.println(0);
				}
				break;
			case 5:
				if (!stack.isEmpty()) {
					sb.append(stack.peek()).append("\n");
//					System.out.println(stack.peek());
				} else {
					sb.append(-1).append("\n");
//					System.out.println(-1);
				}
				break;
			}

//            if (O == 1) {
//                int X = Integer.parseInt(st.nextToken());
//                stack.add(X);
//            } else if (O == 2) {
//                if (!stack.isEmpty()) {
//                    System.out.println(stack.pop());
//                } else {
//                    System.out.println(-1);
//                }
//            } else if (O == 3) {
//                System.out.println(stack.size());
//            } else if (O == 4) {
//                if (stack.isEmpty()) {
//                    System.out.println(1);
//                } else {
//                    System.out.println(0);
//                }
//            } else {
//                if (!stack.isEmpty()) {
//                    System.out.println(stack.peek());
//                } else {
//                    System.out.println(-1);
//                }
//            }
		}
		br.close();
		System.out.println(sb);
	}
}