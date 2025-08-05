import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static StringBuilder answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		TreeMap<String, TreeMap> root = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			
			TreeMap<String, TreeMap> map = root;
			for (int t = 0; t < T; t++) {
				String key = st.nextToken();
				map.putIfAbsent(key, new TreeMap<String, TreeMap>());
				map = map.get(key);
			}
		}
		
		answer = new StringBuilder();
		for (String key : root.keySet()) {
			visualize(key, root, 0);
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static void visualize(String keyword, TreeMap<String, TreeMap> map, int depth) {
		for (int i = 0; i < depth; i++) {
			answer.append("--");
		}
		answer.append(keyword).append("\n");
		
		TreeMap<String, TreeMap> nextMap = map.get(keyword);
		if (nextMap == null || nextMap.isEmpty()) return;
		
		for (String nextKey : nextMap.keySet()) {
			visualize(nextKey, nextMap, depth + 1);
		}
	}
}
