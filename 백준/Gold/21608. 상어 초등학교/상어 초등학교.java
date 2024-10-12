import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static int[][] shark;
	static boolean[][] visit;
	static Map<Integer, List<Integer>> map;
	static List<Integer> student;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new HashMap<>();			// map 초기화
		student = new ArrayList<>();	// 앉을 학생들의 순서를 저장할 배열
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			map.put(key, new ArrayList<>());
			student.add(key);
			
			for (int j = 0; j < 4; j++) {
				map.get(key).add(Integer.parseInt(st.nextToken()));	// key번 학생이 좋아하는 학생들을 map에 저장
			}
		}
		
		shark = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N * N; i++) {
			int key = student.get(i);			// 이번 차례에 앉을 학생의 번호
			int maxFriend = 0;					// 인접한 자리에 앉은 좋아하는 학생 수의 최대
			int maxEmpty = 0;					// 인접한 자리 중 빈 자리의 최대
			int R = 0;							// 학생이 앉을 자리
			int C = 0;
			
			for (int r = 0; r < N; r++) {		// 자리를 완전탐색
				for (int c = 0; c < N; c++) {
					if (visit[r][c]) continue;	// (r, c)에 학생이 앉아있는 경우
					
					int cntFriend = 0;				// 인접한 자리에 앉은 좋아하는 학생 수
					int cntEmpty = 0;				// 인접한 자리 중 비어있는 자리 수
					for (int d = 0; d < 4; d++) {	// 4방탐색
						int NR = r + dr[d];
						int NC = c + dc[d];
						
						if (NR >= 0 && NR < N && NC >= 0 && NC < N) {
							if (map.get(key).contains(shark[NR][NC])) {	// 인접한 자리에 앉은 학생이 좋아하는 학생인 경우
								cntFriend++;
							} else if (!visit[NR][NC]) {				// 인접한 자리가 빈 자리인 경우
								cntEmpty++;
							}
						}
					}	// 4방탐색 끝
					
					if (maxFriend < cntFriend) {	// 인접한 자리에 좋아하는 학생들이 가장 많은 자리가 현재 자리인 경우
						R = r;
						C = c;
						maxFriend = cntFriend;
						maxEmpty = cntEmpty;
					} else if (maxFriend == cntFriend && maxEmpty < cntEmpty) {
						R = r;
						C = c;
						maxEmpty = cntEmpty;
					}
				}
			}	// key학생에 대한 자리탐색 끝
			
			boolean flag = false;
			if (maxFriend == 0 && maxEmpty == 0) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (!visit[r][c]) {
							shark[r][c] = key;
							visit[r][c] = true;
							flag = true;
							break;
						}
					}
					
					if (flag) break;
				}
			} else {
				shark[R][C] = key;		// key학생을 (R, C)에 앉히고
				visit[R][C] = true;		// 빈 자리를 채운다.
			}
		}
		
		for (int r = 0; r < N; r++) {			// 만족도 조사
			for (int c = 0; c < N; c++) {
				int key = shark[r][c];
				int count = 0;
				
				for (int d = 0; d < 4; d++) {	// 4방탐색
					int NR = r + dr[d];
					int NC = c + dc[d];
					
					if (NR >= 0 && NR < N && NC >= 0 && NC < N) {
						if (map.get(key).contains(shark[NR][NC])) {	// 인접한 위치에 좋아하는 학생이 앉은 경우
							count++;								// count를 1만큼 증가시킨다.
						}
					}
				}
				
				if (count == 1) {
					answer++;
				} else if (count == 2) {
					answer += 10;
				} else if (count == 3) {
					answer += 100;
				} else if (count == 4) {
					answer += 1000;
				}
			}
		}
		
		System.out.println(answer);
	}
}