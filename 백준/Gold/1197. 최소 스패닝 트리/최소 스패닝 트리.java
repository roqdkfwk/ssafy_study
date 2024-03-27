import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static int[][] edges;	// 간선의 정보
	static int[] p;	// 대표를 저장할 배열
	static int ans;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edges = new int[E][3];
		for (int i = 0; i < E; i++) {
			
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
			edges[i][2] = Integer.parseInt(st.nextToken());
		}	// 간선의 정보를 입력
		
//		for (int[] edge : edges) {
//			System.out.println(Arrays.toString(edge));
//		}
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				return (o1[2] - o2[2]) > 0 ? 1 : -1;
			}			
		});	// edges배열을 가충치 기준 오름차순으로 정렬
		
//		for (int[] edge : edges) {
//			System.out.println(Arrays.toString(edge));
//		}
		
		ans = 0;
		count = 0;
		
		p = new int[V + 1];
		for (int i = 1; i < V + 1; i++) p[i] = i;
		for (int i = 0; i < E; i++) { 
			
			int px = findset(edges[i][0]);
			int py = findset(edges[i][1]);
			
			if (px != py) {
				
				union(px, py);
				ans += edges[i][2];
				count++;
			}
			
			if (count == V - 1) break;
		}	// 간선을 잇는 작업
		
		System.out.println(ans);
	}

	static void union(int x, int y) {
		
		p[findset(y)] = findset(x);
	}	// union

	static int findset(int x) {
		
		if (x != p[x])	// 대표자가 아니라면
			p[x] = findset(p[x]);
		return p[x];
	}	// findset
}