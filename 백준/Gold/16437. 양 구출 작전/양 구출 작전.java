import java.util.*;
import java.io.*;
public class Main {
	
	static class Island {
		char animal;
		int population;
		int parent;
		
		public Island(char animal, int population, int parent) {
			this.animal = animal;
			this.population = population;
			this.parent = parent;
		}
	}
	
	static int N;						// 섬의 개수
	static Island[] islands;			// 섬들의 정보 저장
	static List<Integer>[] children;	// 각 섬의 자식 섬들
	static long answer;					// 구출할 수 있는 양
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		islands = new Island[N + 1];
		children = new List[N + 1];
		
		// 초기화
		for (int i = 1; i <= N; i++) {
			children[i] = new ArrayList<>();
		}
		
		islands[1] = new Island('X', 0, 0);
		
		// 섬끼리 연결
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			char animal = st.nextToken().charAt(0);
			int population = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			
			islands[i + 2] = new Island(animal, population, parent);
			children[parent].add(i + 2);  // 부모-자식 관계 설정
		}
		
		// 1번 섬에서 DFS 시작
		answer = dfs(1);
		
		// 구출한 양의 수를 출력
		System.out.println(answer);
	}
	
	/**
	 * DFS를 사용해서 각 섬에서 1번 섬으로 올라오는 양의 수를 계산
	 */
	public static long dfs(int island) {
		long sheepCount = 0;
		
		// 자식 섬들로부터 올라오는 양의 수를 모두 합산
		for (int child : children[island]) {
			sheepCount += dfs(child);
		}
		
		// 현재 섬에서의 처리
		if (islands[island].animal == 'S') {
			// 양이 있는 섬인 경우
			sheepCount += islands[island].population;
		} else if (islands[island].animal == 'W') {
			// 늑대가 있는 섬인 경우
			sheepCount = Math.max(0, sheepCount - islands[island].population);
		}
		
		return sheepCount;
	}
}