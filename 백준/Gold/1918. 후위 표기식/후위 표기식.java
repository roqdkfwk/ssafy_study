import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 중위 표기식
		String str = sc.next();
		String postfix = "";
		
		Stack<Character> op = new Stack<>();
		Map<Character, Integer> priority = new HashMap<>();
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
		priority.put('(', 0);
		
		// 연산자들을 연산자 스택에 정리
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			// 숫자인 경우
			if ('A' <= c && c <= 'Z') {
				postfix += c;
			// 여는 괄호
			} else if (c == '(') {
				op.push(c);
//				if (op.isEmpty()) {
//					op.push(c);
//				} else {
//					while (!op.isEmpty() && priority.get(c) <= priority.get(op.peek())) {
//						postfix += op.pop();
//					}
//					op.push(c);
//				}
			} else if (c == ')') {
				while (!op.isEmpty() && op.peek() != '(') {
					postfix += op.pop();
				}
				op.pop();	// 괄호 마무리
			// 연산자인 경우
			} else {
				if (op.isEmpty()) op.push(c);
				else {
					while (!op.isEmpty() && priority.get(c) <= priority.get(op.peek())) {
						postfix += op.pop();
					}
					op.push(c);
				}
			}
		}
		
		while (!op.isEmpty()) {
			postfix += op.pop();
		}
		System.out.println(postfix);
	}
}