import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] 공유기위치 = new int[N];
		for (int i = 0; i < N; i++) {
			공유기위치[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(공유기위치);
		
		int left = 1;
		int right = 공유기위치[N - 1] + 1;
		while (left < right) {
			int mid = left + ((right - left) / 2);
			
			if (canInstall(공유기위치, C, mid)) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		System.out.println(left - 1);
	}
	
	private static boolean canInstall(int[] location, int 목표개수, int 간격) {
		int 이전공유기위치 = location[0];
		int 개수 = 1;
		for (int i = 1; i < location.length; i++) {
			if (location[i] - 이전공유기위치 < 간격) continue;
				
			이전공유기위치 = location[i];
			개수++;
		}
		
		return 개수 >= 목표개수;
	}
}