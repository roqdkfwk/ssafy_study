import java.util.*;
import java.io.*;
public class Main {

	static int N;
	static PriorityQueue<int[]> pq;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}
	
	static void printResult() {
		System.out.println(answer);
	}
	
	static void Solution() {
		PriorityQueue<Integer> selected = new PriorityQueue<>();
	    
	    while (!pq.isEmpty()) {
	        int[] curr = pq.poll();
	        selected.add(curr[1]);
	        
	        // 현재 데드라인보다 많은 문제를 선택했다면, 가장 작은 컵라면 수를 제거
	        if (selected.size() > curr[0])
	            selected.poll();
	    }
	    
	    // 선택된 모든 컵라면 수의 합을 계산
	    while (!selected.isEmpty())
	        answer += selected.poll();
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		/*
		 * 데드라인 오름차순으로,
		 * 데드라인이 같은 경우 받을 수 있는 컵라면의 개수를 기준으로 내림차순으로 정렬
		 */
		pq = new PriorityQueue<>((a, b) ->{
			if (a[0] == b[0])
				return b[1] - a[1];
			return a[0] - b[0];
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int deadLine = Integer.parseInt(st.nextToken());
			int ramyeon = Integer.parseInt(st.nextToken());
			pq.add(new int[] {deadLine, ramyeon});
		}
	}
}