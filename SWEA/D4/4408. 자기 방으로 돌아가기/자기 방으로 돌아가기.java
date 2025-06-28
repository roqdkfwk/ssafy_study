import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			// 정렬
			int[][] rooms = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				rooms[i][0] = Integer.parseInt(st.nextToken()) - 1;
				rooms[i][1] = Integer.parseInt(st.nextToken()) - 1;
				Arrays.sort(rooms[i]);
			}
			Arrays.sort(rooms, (a, b) -> Integer.compare(a[1], b[1]));
			
			int minTime = 0;
			// 지나는 복도 경로를 확인
			int[] corridor = new int[200];
			for (int i = 0; i < N; i++) {
				for (int index = rooms[i][0] / 2; index <= rooms[i][1] / 2; index++) {
					corridor[index]++;
					minTime = Math.max(minTime, corridor[index]);
				}
			}
			
			answer.append("#" + t + " ").append(minTime).append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
}