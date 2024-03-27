import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N;	// 컴퓨터의 수
	static int M;	// 간선의 수
	static int[][] edges;	
	static int ans;	// 총 비용
	static int count;	// 연결한 간선의 개수
	static int[] p;	// 대표를 저장할 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edges = new int[M][3];		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
			edges[i][2] = Integer.parseInt(st.nextToken());
		}	// 간선 정보들을 입력
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] e1, int[] e2) {
				
				return (e1[2] - e2[2]) < 0 ? -1 : 1;
			}
		});	// 간선의 비용을 기준으로 오름차순으로 정렬
		
		ans = 0;
		count = 0;
		
		p = new int[N + 1];
		for (int i = 1; i < N + 1; i++) p[i] = i;	// 대표를 자기 자신으로 초기화
		
		for (int i = 0; i < M; i++) {	// 간선의 개수만큼 반복
			
			int px = findset(edges[i][0]);
			int py = findset(edges[i][1]);
			
			if (px != py) {	// px와 py의 대표자가 다르다면 연결해야함
				
				union(px, py);
				ans += edges[i][2];
				count++;
			}
			
			if (count == N - 1) break;	// 모든 컴퓨터를 연결하는 데 필요한 최소 간선의 개수를 만족했다면 반복문을 멈춤
		}	// 컴퓨터들을 연결
		
		System.out.println(ans);
	}

	static void union(int px, int py) {
		
		p[findset(py)] = findset(px);	// py 집합의 대표자를 px집합의 대표자에 붙임
	}

	static int findset(int x) {
		
		if (x != p[x])	// x가 대표자가 아니라면
			p[x] = findset(p[x]);
		return p[x];
	}
}