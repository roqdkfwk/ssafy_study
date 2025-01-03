import java.util.*;
import java.io.*;
public class Main {

	static int M, N;
	static List<List<Integer>> universes;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}
	
	static void printResult() {
		System.out.println(answer);
	}
	
	static boolean checkUniformity(List<Integer> u1, List<Integer> u2) {
		for (int i = 0; i < N; i++)
			if (!u1.get(i).equals(u2.get(i)))
				return false;
		return true;
	}
	
	static void rankPlanet(HashMap<Integer, Integer> rankSize, int num) {
		List<Integer> universe = universes.get(num);
	    for (int i = 0; i < universe.size(); i++)
	        universe.set(i, rankSize.get(universe.get(i)));
	}
	
	static HashMap<Integer, Integer> sortPlanet(List<Integer> universe) {
		List<Integer> sortedUniverse = new ArrayList<>(universe);
		Collections.sort(sortedUniverse);
		
		HashMap<Integer, Integer> rankSize = new HashMap<>();
		int rank = 0;
		for (int size : sortedUniverse) {
			if (rankSize.get(size) == null) {
				rankSize.put(size, rank);
				rank++;
			}
		}
		
		return rankSize;
	}
	
	static void Solution() {
		for (int i = 0; i < M; i++) {
			// 1. 행성들을 정렬
			HashMap<Integer, Integer> rankSize = sortPlanet(universes.get(i));
			
			// 2. 정렬한 행성들의 크기 순위를 메긴다.
			rankPlanet(rankSize, i);
		}
		
		// 3. 균등성 검사
		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				if (checkUniformity(universes.get(i), universes.get(j)))
					answer++;
			}
		}
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		universes = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			universes.add(new ArrayList<>());
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				universes.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
	}
}