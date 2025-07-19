import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static int[][] arr;
	static long totalPeoples;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			
			totalPeoples += arr[i][1];
		}
		
		Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
		
		long acc = 0L;
		for (int i = 0; i < N; i++) {
			acc += arr[i][1];
			
			if (acc >= (totalPeoples + 1) / 2) {
				System.out.println(arr[i][0]);
				return;
			}
		}
	}
}
