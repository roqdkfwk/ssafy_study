import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// arrayindexoutofbound
		// nullpointer
		
//		String str2 = "";
//		System.out.println(str2.length());
		
		// 백스페이스 -
		// 화살표 <, >
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		List<Character> strList;
		Stack<Character> stack;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			strList = new ArrayList<>();
			stack = new Stack<>();
			
			String str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				
				if ('0' <= c && c <= '9' || 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
					strList.add(c);
				// 왼쪽 화살표가 나오면
				} else if (c == '<') {
					// strList에 있던 마지막 문자를 stack에 쌓고
					// 그 문자를 strList에서 삭제
					if (strList.size() != 0) {
						stack.push(strList.get(strList.size() - 1));
						strList.remove(strList.size() - 1);
					}
				// 오른쪽 화살표가 나오면
				} else if (c == '>') {
					// stack에 있던 문자를 pop해서 strList에 add
					if (!stack.isEmpty()) strList.add(stack.pop());
				// 백스페이스가 나오면 마지막 문자를 지움
				} else {
					if (strList.size() != 0) strList.remove(strList.size() - 1);
				}
			}
			
			// stack에 남아있는 문자들을 pop해서 strList에 add해줌
			while (!stack.isEmpty()) strList.add(stack.pop());
			
			sb = new StringBuilder();
			for (int i = 0; i < strList.size(); i++) sb.append(strList.get(i));				
			
			System.out.println(sb);
		}
	}
}