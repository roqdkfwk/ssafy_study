import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] pop;
	static int[] area;
	static boolean[] visit;
	static ArrayList<Integer>[] list;
	static Queue<Integer> queue;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pop = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) 
			pop[i] = Integer.parseInt(st.nextToken());
 		
		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++)
			list[i] = new ArrayList<Integer>();
		
		for (int i = 1; i < N + 1; i++) {
			
			st = new StringTokenizer(br.readLine());
			int near = Integer.parseInt(st.nextToken());
			for (int j = 0; j < near; j++)
				list[i].add(Integer.parseInt(st.nextToken()));
		}
		
		area = new int[N + 1];
		DFS(1);
		
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}	// main

	private static void DFS(int num) {

		if (num == N + 1) {
			int area1 = 0;
			int area2 = 0;
			for (int i = 1; i < N + 1; i++) {
				
				if (area[i] == 1) area1 += pop[i];
				else area2 += pop[i];
			}
			
			visit = new boolean[N + 1];
			int con = 0;
			
			for (int i = 1; i < N + 1; i++) {
				if (!visit[i]) {
					BFS(i, area[i]);
					con++;
				}
			}
			
			if (con == 2)
				min = Math.min(min, Math.abs(area1 - area2));
			
			return;
		}
		
		area[num] = 1;
		DFS(num + 1);
		
		area[num] = 2;
		DFS(num + 1);
	}	// DFS

	private static void BFS(int idx, int areaNum) {

		queue = new LinkedList<>();
		visit[idx] = true;
		queue.add(idx);
		
		while (!queue.isEmpty()) {
			
			int now = queue.poll();
			for (int i = 0; i < list[now].size(); i++) {
				
				int next = list[now].get(i);
				if (area[next] == areaNum && !visit[next]) {
					queue.add(next);
					visit[next] = true;
				}
			}
		}
	}	// BFS
}