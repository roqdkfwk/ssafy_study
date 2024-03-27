import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 개수
	static int N, M;
	static int[] p;	// 대표를 저장할 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			p = new int[N + 1]; 
			for (int i = 1; i < N + 1; i++) p[i] = i;	// 나 자신을 대표로 초기화
			
			for(int i = 0; i < M; i++) {
				
				st = new StringTokenizer(br.readLine());
				int oper = Integer.parseInt(st.nextToken());
				int V1 = Integer.parseInt(st.nextToken());
				int V2 = Integer.parseInt(st.nextToken());
				
				if (oper == 0) {	// oper == 0이면 union실행
					if (findset(V1) != findset(V2)) {	// V1과 V2의 대표가 다르다면
						union(V1, V2);	// 둘을 합침
					}
				} else {
					if (findset(V1) == findset(V2)) sb.append(1);
					else sb.append(0);
				}
			}
			
			sb.append("\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}

	static void union(int x, int y) {
	
		p[findset(y)] = findset(x);
	}	// union 메소드

	static int findset(int x) {
		
		// x == p[x]이면 x가 대표 
		// x != p[x]이면 x는 대표가 아니라는 뜻
		// x가 대표가 아니라면 x의 대표(p[x])에다가 findset(p[x])로 x의 대표를 찾아줌
		if (x != p[x])
			p[x] = findset(p[x]);
		return p[x];
	}	// findset 메소드
}