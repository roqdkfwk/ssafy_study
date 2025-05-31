import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] score = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] maxScore = new int[N][3];
		int[][] minScore = new int[N][3];
		for (int i = 0; i < 3; i++) {
			maxScore[0][i] = minScore[0][i] = score[0][i];
		}
		
		for (int i = 1; i < N; i++) {
			maxScore[i][0] = Math.max(maxScore[i - 1][0], maxScore[i - 1][1]) + score[i][0];
			maxScore[i][1] = Math.max(Math.max(maxScore[i - 1][0], maxScore[i - 1][1]), maxScore[i - 1][2]) + score[i][1];
			maxScore[i][2] = Math.max(maxScore[i - 1][1], maxScore[i - 1][2]) + score[i][2];
			
			minScore[i][0] = Math.min(minScore[i - 1][0], minScore[i - 1][1]) + score[i][0];
			minScore[i][1] = Math.min(Math.min(minScore[i - 1][0], minScore[i - 1][1]), minScore[i - 1][2]) + score[i][1];
			minScore[i][2] = Math.min(minScore[i - 1][1], minScore[i - 1][2]) + score[i][2];
		}
		
		System.out.print(Math.max(Math.max(maxScore[N - 1][0], maxScore[N - 1][1]), maxScore[N - 1][2]) + " ");
		System.out.print(Math.min(Math.min(minScore[N - 1][0], minScore[N - 1][1]), minScore[N - 1][2]));
	}
}