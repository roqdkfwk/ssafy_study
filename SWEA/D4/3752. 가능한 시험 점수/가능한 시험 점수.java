import java.util.*;
import java.io.*;
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] scores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			Set<Integer> scoreSet = new HashSet<>();
			scoreSet.add(0);
			for (int i = 0; i < scores.length; i++) {
				int[] scoreArray = scoreSet.stream().mapToInt(Integer::intValue).toArray();
				for (int score : scoreArray) {
					scoreSet.add(score + scores[i]);
				}
			}
		
			answer.append("#" + (t + 1) + " ").append(scoreSet.size()).append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
}