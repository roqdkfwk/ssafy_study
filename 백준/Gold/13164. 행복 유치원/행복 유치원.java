import java.util.*;
import java.io.*;
public class Main {
	
	static int N;	// 원생 수
	static int K;	// 조의 수
	static int[] heights;
	static int[] differences;
	static long answer;
	
	/**
	 * 각 조에는 원생이 적어도 한 명 이상
	 * 같은 조에 속한 원생들은 서로 인접
	 * >> 원생들을 키 순서대로 정렬 후 조의 개수만큼 나눈다
	 * 키가 많이 차이나는 구간 (K-1)개를 자르면 K개의 조로 나눌 수 있음
	 * 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		heights = new int[N];
		heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(heights);
		
		differences = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			differences[i] = heights[i + 1] - heights[i];
		}
		Arrays.sort(differences);
		
		for (int i = 0; i < N - K; i++) {
			answer += differences[i];
		}
		
		System.out.println(answer);
	}
}
