import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] arr;
	static PriorityQueue<int[]> pq;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (a, b) -> {
			if (a[1] == b[1]) {
				return a[0] - b[0];
			} 
			return a[1] - b[1];
		});
		
		pq = new PriorityQueue<int[]>((a, b) -> {
			return a[1] - b[1];
		});
		
		pq.add(arr[0]);
		answer = 1;;
		for (int i = 1; i < N; i++) {
			if (pq.peek()[1] <= arr[i][0]) {
				pq.poll();
				pq.add(arr[i]);
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}