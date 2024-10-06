import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] heights;
	static int[] result;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());	// 탑의 수
        heights = new int[N]; 					// 탑의 높이를 저장하는 배열
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[N];					// 결과를 저장할 배열
        Stack<Integer> stack = new Stack<>();	// 스택을 사용하여 각 탑의 인덱스를 저장 (탑 번호)

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {	// 현재 탑의 높이보다 낮은 탑은 신호를 받을 수 없으므로 제거
                stack.pop();
            }
            
            if (!stack.isEmpty()) {												// 스택이 비어 있지 않으면, 스택의 최상단에 있는 탑이 신호를 받음
                result[i] = stack.peek() + 1; 									// 1-based index
            } else {
                result[i] = 0;													// 수신할 탑이 없으면 0
            }
            
            stack.add(i);														// 현재 탑의 인덱스를 스택에 추가
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}