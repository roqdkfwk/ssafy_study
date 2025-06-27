import java.util.*;
import java.io.*;
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		
		/**
		 * 한 주에 포함된 수업의 수, 몫
		 * 첫 수업을 듣는 날짜 선택
		 */
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int day = 0;
			int N = Integer.parseInt(br.readLine());
			
			// 수업을 하루만 들어도 되는 경우
			if (N == 1) {
				answer.append("#" + t + " ").append(1).append("\n");
				continue;
			}
			
			int count = 0;
			int[] week = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < 7; i++) {
				if (week[i] == 1) count++; 
			}
			
			// 필요한 수업 일수가 일주일 동안 진행되는 수업 일수로 나누어 떨어지는 경우
			int minDay = 7;
			if (N % count == 0) {
				day += (N / count) * 7 - 7;
				int leftClass = count;
				for (int i = 0; i < 7; i++) {
					int openedClass = 0;
					for (int j = 0; j < 6; j++) {
						if (week[(i + j) % 7] == 1) openedClass++;
						
						if (openedClass == leftClass) minDay = Math.min(minDay, j + 1);
					}
				}
			}
			// 필요한 수업 일수가 일주일 동안 진행되는 수업 일수로 나누어 떨어지지 않는 경우
			else {
				day += (N / count) * 7;
				
				int leftClass = N % count;
				for (int i = 0; i < 7; i++) {
					int openedClass = 0;
					for (int j = 0; j < 6; j++) {
						if (week[(i + j) % 7] == 1) openedClass++;
						
						if (openedClass == leftClass) minDay = Math.min(minDay, j + 1);
					}
				}
			}
			answer.append("#" + t + " ").append(day + minDay).append("\n");
		}
		System.out.println(answer.toString().trim());
	}
}