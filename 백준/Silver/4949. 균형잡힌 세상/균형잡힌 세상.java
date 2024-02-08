import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            
            if (str.equals(".")) {
//                System.out.println("문장 끝.");
                break;
            }
            
            boolean flag = true;
            for (int i = 0; i < str.length() - 1; i++) {
                // 괄호이면 스택에 쌓는다.
                if (str.charAt(i) == '(' || str.charAt(i) == '[') {
                    stack.add(str.charAt(i));
                } else if (str.charAt(i) == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        flag = false;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                } else if (str.charAt(i) == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        flag = false;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }
            }
            
            if (flag == true && stack.isEmpty()) System.out.println("yes");
            else System.out.println("no");
        }
    }
}