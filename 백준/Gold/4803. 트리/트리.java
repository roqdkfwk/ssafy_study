import java.util.*;
import java.io.*;
public class Main {

	static List<Integer>[] graph;
	static boolean[] visited;
	static int nodeCount, edgeCount;
	static int n, m;
	public static void main(String[] args) throws IOException {
		inputHandler();
	}
	
	private static void inputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int caseNum = 1;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if (n == 0 && m == 0) break;
			
			graph = new ArrayList[n + 1];
			visited = new boolean[n + 1];
			
			for (int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				graph[b].add(a);
			}
			
			solution(caseNum);
			caseNum++;
			
		}
	}
	
	private static void solution(int caseNum) {
		int treeCount = 0;
		
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				nodeCount = 0;
				edgeCount = 0;
				
				dfs(i);
				
				if (edgeCount / 2 == nodeCount - 1) {
					treeCount++;
				}
			}
		}
		
		printResult(caseNum, treeCount);
	}
	
	private static void dfs(int now) {
		visited[now] = true;
		nodeCount++;
		
		for (int next : graph[now]) {
			edgeCount++;
			
			if (!visited[next]) {
				dfs(next);
			}
		}
	}
	
	private static void printResult(int caseNum, int treeCount) {
        System.out.print("Case " + caseNum + ": ");

        if (treeCount == 0)
            System.out.println("No trees.");
        else if (treeCount == 1)
            System.out.println("There is one tree.");
        else
            System.out.println("A forest of " + treeCount + " trees.");
    }
}
