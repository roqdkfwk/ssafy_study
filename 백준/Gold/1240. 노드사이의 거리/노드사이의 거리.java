import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static List<Node>[] list;
	static List<int[]> nodeNum;
	static boolean[] visit;
	static int ans;
	
	static class Node {
		
		int next;
		int dist;
		
		Node (int next, int dist){
			this.next = next;
			this.dist = dist;
		}
	}	// Node class
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++)
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < N - 1; i++) {
			
			st = new StringTokenizer(br.readLine());
			int no1 = Integer.parseInt(st.nextToken());
			int no2 = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			list[no1].add(new Node(no2, dis));
			list[no2].add(new Node(no1, dis));
		}
		
		nodeNum = new ArrayList<int[]>();
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			nodeNum.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		for (int i = 0; i < nodeNum.size(); i++) {
			
			int[] node = nodeNum.get(i);
			
			visit = new boolean[N + 1];
			DFS(node[0], node[1], 0);
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}	// main

	private static void DFS(int from, int to, int distance) {

		if (from == to) {
			
			ans = distance;
			return;
		}
		
		visit[from] = true;
		
		for (int i = 0; i < list[from].size(); i++) {
			
			if (!visit[list[from].get(i).next])
				DFS(list[from].get(i).next, to, distance + list[from].get(i).dist);
		}
	}	// DFS
}