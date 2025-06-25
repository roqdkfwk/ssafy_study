import java.util.*;
import java.io.*;
public class Solution {
	
	static int B, W, X, Y, Z;
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			B = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Z = Integer.parseInt(st.nextToken());
			
			int score = -(int)1e9;
			for (int c = 0; c <= Math.min(B,  W); c++) {
				score = Math.max(score, B * X + W * Y - c * (X + Y) + 2 * c * Z);
			}
			answer.append(score).append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
}