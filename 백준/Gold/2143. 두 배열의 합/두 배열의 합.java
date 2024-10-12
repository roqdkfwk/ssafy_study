import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static int T, N, M;
	static int[] A, B;
	static Map<Integer, Integer> subA;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		subA = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = i; j < N; j++) {
				sum += A[j];
				subA.put(sum, subA.getOrDefault(sum, 0) + 1);
			}
		}
		
		for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int j = i; j < M; j++) {
				sum += B[j];
				int key = T - sum;
				if (subA.containsKey(key)) {
					answer += subA.get(key);
				}
			}
		}
		
		System.out.println(answer);
	}	// main
}