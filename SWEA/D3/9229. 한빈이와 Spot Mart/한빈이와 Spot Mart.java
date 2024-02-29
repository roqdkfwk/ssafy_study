import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;	// 과자 봉지의 개수
	static int M;	// 무게 합 제한
	static int weight = 0;
	static int[] snack;	// 과자 무게를 저장할 배열
	static int maxWeight;	// 과자 무게의 합의 최댓값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringBuilder sb = new StringBuilder();
		 StringTokenizer st;
		 
		 int T = Integer.parseInt(br.readLine()); 
		 for (int t = 1; t <= T; t++) {

			 sb.append("#" + t + " ");
			 
			 maxWeight = -1;
			 
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 M = Integer.parseInt(st.nextToken());
			 snack = new int[N];
			 
			 st = new StringTokenizer(br.readLine());
			 for (int i = 0; i < N; i++) snack[i] = Integer.parseInt(st.nextToken());
			 
			 for (int i = 0; i < N - 1; i++) {
				 for (int j = i + 1; j < N; j++) {
					 
					 if (snack[i] + snack[j] <= M)	{	// 무게 제한을 넘지 않는데
						 if (maxWeight < snack[i] + snack[j])	maxWeight = snack[i] + snack[j];	// 최댓값보다 크다면 최댓값을 갱신
					 }
				 }
			 }
			 
			 sb.append(maxWeight + "\n");
		 }
		 
		 System.out.println(sb);
		
		
	}
}