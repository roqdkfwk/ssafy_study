import java.util.*;
import java.io.*;
public class Main {
	
	static int[] 행이동 = {0, -1, 0};	// 좌, 상, 우
	static int[] 열이동 = {-1, 0, 1};	// 좌, 상, 우
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int 행크기 = Integer.parseInt(st.nextToken());
		int 열크기 = Integer.parseInt(st.nextToken());
		int 사거리 = Integer.parseInt(st.nextToken());
		
		int[][] castle = new int[행크기][열크기];
		for (int 행 = 0; 행 < 행크기; 행++) {
			st = new StringTokenizer(br.readLine());
			for (int 열 = 0; 열 < 열크기; 열++) {
				castle[행][열] = Integer.parseInt(st.nextToken());
			}
		}
		
		int 정답 = 0;
		for (int 첫째 = 0; 첫째 < 열크기 - 2; 첫째++) {
			for (int 둘째 = 첫째 + 1; 둘째 < 열크기 - 1; 둘째++) {
				if (첫째 == 둘째) continue;
				for (int 셋째 = 둘째 + 1; 셋째 < 열크기; 셋째++) {
					if (둘째 == 셋째) continue;
					
					정답 = Math.max(정답, 적제거(castle, new int[] {첫째, 둘째, 셋째}, 사거리));
				}
			}
		}
		
		System.out.println(정답);
	}
	
	private static int 적제거(int[][] 기존격자, int[] 궁수, int 사거리) {
		int 행크기 = 기존격자.length;
		int 열크기 = 기존격자[0].length;

		// 1번만 복사
		int[][] 복사격자 = new int[행크기][열크기];
		for (int 행 = 0; 행 < 행크기; 행++) {
			for (int 열 = 0; 열 < 열크기; 열++) {
				복사격자[행][열] = 기존격자[행][열];
			}
		}

		int 제거한_적 = 0;
		while (true) {
			// 1. 제거할 수 있는 적 탐색
			Set<String> 적위치 = new HashSet<>();
			for (int i = 0; i < 3; i++) {
				int[] 적 = 적탐색(복사격자, 궁수[i], 행크기, 열크기, 사거리);
				
				if (적 != null) {
					적위치.add(적[0] + "," + 적[1]);
				}
			}

			// 2. 적 제거
			for (String 위치 : 적위치) {
				String[] 좌표 = 위치.split(",");
				int 행 = Integer.parseInt(좌표[0]);
				int 열 = Integer.parseInt(좌표[1]);
				if (복사격자[행][열] == 1) {
					복사격자[행][열] = 0;
					제거한_적++;
				}
			}

			// 3. 적 아래로 이동
			for (int 행 = 행크기 - 2; 행 >= 0; 행--) {
				for (int 열 = 0; 열 < 열크기; 열++) {
					복사격자[행 + 1][열] = 복사격자[행][열];
				}
			}
			// 첫 번째 행은 빈칸으로 초기화
			Arrays.fill(복사격자[0], 0);

			// 4. 적이 모두 제거되었으면 종료
			boolean 적남아있음 = false;
			for (int r = 0; r < 행크기; r++) {
				for (int c = 0; c < 열크기; c++) {
					if (복사격자[r][c] == 1) {
						적남아있음 = true;
						break;
					}
				}
				if (적남아있음) break;
			}
			if (!적남아있음) break;
		}

		return 제거한_적;
	}

	
	private static int[] 적탐색(int[][] 격자, int 궁수, int 행크기, int 열크기, int 사거리) {
		int[][] 복사격자 = new int[행크기 + 1][열크기];
		for (int 행 = 0; 행 < 행크기; 행++) {
			for (int 열 = 0; 열 < 열크기; 열++) {
				복사격자[행][열] = 격자[행][열];
			}
		}
		
		Deque<int[]> 사격범위 = new ArrayDeque<int[]>();
		boolean[][] 방문 = new boolean[행크기 + 1][열크기];
		사격범위.add(new int[] {행크기, 궁수});
		방문[행크기][궁수] = true;
		
		for (int 범위 = 0; 범위 < 사거리; 범위++) {
			int 크기 = 사격범위.size();
			for (int i = 0; i < 크기; i++) {
				int[] 현위치 = 사격범위.poll();
				
				for (int d = 0; d < 3; d++) {
					int 다음행 = 현위치[0] + 행이동[d];
					int 다음열 = 현위치[1] + 열이동[d];
					
					if (다음행 < 0 || 다음열 < 0 || 다음열 >= 열크기 || 방문[다음행][다음열]) continue;
					
					if (복사격자[다음행][다음열] == 1) {
						return new int[] {다음행, 다음열};
					}
					사격범위.add(new int[] {다음행, 다음열});
				}
			}
		}
		
		return null;
	}
}