import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static Map<Integer, Integer> A;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int key = Integer.parseInt(st.nextToken());
			A.put(key, A.getOrDefault(key, 0) + 1);
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int key = Integer.parseInt(st.nextToken());
			if (A.containsKey(key)) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}	// main
}