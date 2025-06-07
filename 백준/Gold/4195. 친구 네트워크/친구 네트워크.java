import java.util.*;
import java.io.*;
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			Map<String, String> parent = new HashMap<>();		// Map<친구, 부모>
			Map<String, Set<String>> network = new HashMap<>();	// Map<친구, 친구들>
			
			int F = Integer.parseInt(br.readLine());
			for (int f = 0; f < F; f++) {
				String[] str = br.readLine().split(" ");
				String p1 = str[0];
				String p2 = str[1];
				
				// 속한 네트워크가 없는 경우
				if (parent.get(p1) == null) {
					parent.put(p1, p1);
					network.put(p1, new HashSet<>());
				}
				
				if (parent.get(p2) == null) {
					parent.put(p2, p2);
					network.put(p2, new HashSet<>());
				}
				
				// 사전 순으로 앞선 문자열이 부모를 하게 만들기 위해서 정렬
				str[0] = parent.get(p1);
				str[1] = parent.get(p2);
				Arrays.sort(str);
				
				// 서로 다른 네트워크인 경우 네트워크를 합친다.
				if (!(str[0].equals(str[1]))) {
					union(str[0], str[1], parent, network);
				}
				
				answer.append(network.get(str[0]).size() + 1).append("\n");
			}
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static void union(String p1, String p2, Map<String, String> parent, Map<String, Set<String>> network) {
		// 네트워크를 합치는 과정
		parent.put(p2, p1);
		network.get(p1).add(p2);
		
		Set<String> set = network.get(p2);
		for (String p : set) {
			parent.put(p, p1);
			network.get(p1).add(p);
		}
		
		// 메모리 절약을 위해서 흡수된 네트워크는 제거
		network.remove(p2);
	}
}