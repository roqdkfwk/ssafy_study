import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			String str = br.readLine();
			List<Character> stack = new ArrayList<Character>();
			boolean flag = false;
			
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					stack.add('(');
				} else {
					if (stack.contains('(')) {
						stack.remove(stack.indexOf('('));
					} else {
						System.out.println("NO");
						flag = true;
						break;
					}
				}
			}
			
			if (stack.size() > 0) {
				System.out.println("NO");
				flag = true;
			}
			if (flag == false) {
				System.out.println("YES");
			}
		}
	}
}