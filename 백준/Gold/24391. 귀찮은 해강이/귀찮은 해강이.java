import java.util.*;
import java.io.*;
public class Main {
	
	static int N, M;
	static int[] parents;
	static int[] subjects;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N + 1];
		subjects = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 연결된 건물 여부 확인
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			A = findParent(A);
			B = findParent(B);
			
			if (A != B) {
				union(A, B);
			}
		}
		
		int answer = 0;
		subjects = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < N - 1; i++) {
			int s1 = subjects[i];
			int s2 = subjects[i + 1];
			
			// 연결된 건물이 아닌 경우 나와야 하는 횟수 추가
			if (findParent(s1) != findParent(s2)) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	private static int findParent(int X) {
		if (X != parents[X]) {
			parents[X] = findParent(parents[X]);
		}
		return parents[X];
	}
	
	private static void union(int X, int Y) {
	    if (X < Y) {
	    	parents[Y] = X;
	    }
	    else {
	    	parents[X] = Y;
	    }
	}
}
