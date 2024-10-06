import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] time;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		time = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time, (a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			}
			return a[0] - b[0];
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(time[0][1]);
		for (int i = 1; i < N; i++) {
			if (time[i][0] >= pq.peek()) {	// 현재 제일 빨리 끝나는 강의의 시각보다 다음 강의의 시작 시각이 크거나 같다면 
				pq.poll();					// 즉, 가장 빨리 끝나는 강의와 강의 시간이 겹치지 않는다면
				pq.add(time[i][1]);			// 해당 강의가 끝나는 시각을 pq에 추가한다.
			} else {
				pq.add(time[i][1]);			// 강의 시간이 겹치는 경우 강의실을 추가로 배정한다.
			}
		}
		
		System.out.println(pq.size());
	}
}