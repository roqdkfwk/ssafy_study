import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> deque;
        StringTokenizer st;
        StringBuilder sb;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t < T + 1; t++) {
            deque = new LinkedList<Character>();
            int N = Integer.parseInt(br.readLine());
            // 문자를 저장할 배열
            char[] ch = new char[N];
            st = new StringTokenizer(br.readLine());
            // 배열에 입력받은 순서대로 문자를 저장
            for (int i = 0; i < N; i++) ch[i] = st.nextToken().charAt(0);
            
            // 처음에는 문자를 그냥 할당
            deque.offer(ch[0]);
            for (int i = 1; i < N; i++) {            	
                if (ch[i] > deque.peekFirst()) deque.offerLast(ch[i]);
                else deque.offerFirst(ch[i]);
            }
            sb = new StringBuilder();
            while (!deque.isEmpty()) sb.append(deque.pollFirst());
            
            System.out.println(sb);
        }
    }
}