import java.util.*;
import java.io.*;
public class Main {
	
	static int N, M;
	static List<int[]> edges;
    static int[] parent;
    static int answer;
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new int[] {
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
            });
        }
		
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;

        int count = 0;
        for (int[] edge : edges) {
            count++;

            int v1 = find(edge[0]);
            int v2 = find(edge[1]);

            if (v1 == v2) {
                answer = count;
                System.out.println(answer);
                return;
            }

            union(v1, v2);
        }
        
        System.out.println(0);
	}
	
	static int find(int x) {
		if (parent[x] != x)
			parent[x] = find(parent[x]);
		
		return parent[x];
	}
	
	static public void union(int v1, int v2) {
//		int x = find(v1);
//		int y = find(v2);
//
//		if (x != y)
//			parent[y] = x;
		
		if (v1 > v2)
			parent[v1] = v2;
		else
			parent[v2] = v1;
    }
}