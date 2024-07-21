import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;	// 마지막 집합, 관계
	static int[] prnt;	// 부모 번호
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		prnt = new int[n + 1];
		for (int i = 1; i < n + 1; i++)	// 부모 번호를 자기 자신으로 설정
			prnt[i] = i;
		
		for (int i = 0; i < m; i++) {
			
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (oper == 0) union(a, b);
			else {
				if (findset(a) == findset(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
	}	// main

	private static int findset(int a) {
		
		if (a != prnt[a])	// a를 포함하는 집합의 대표자가 a가 아니라면
			prnt[a] = findset(prnt[a]);
		
		return prnt[a];
	}	// find

	private static void union(int a, int b) {
		
		a = findset(a);
		b = findset(b);
		
		if (a != b)	// 두 집합이 서로소 집합이라면
			prnt[a] = b;	// b를 포함하는 집합의 대표자를 a의 대표자로 설정(집합을 합침)		
	}	// union
}