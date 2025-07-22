import java.util.*;
import java.io.*;
public class Main {
	
	static class Poster {
		int start, end;
		
		public Poster(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/**
		 * 크기가 N인 배열을 생성해서 포스터를 붙이는 순서를 배열에 저장
		 * >> 배열의 크기가 최대 1억이므로 시간초과
		 * 
		 * 시간을 줄이는 방법?
		 * 고려 요소 : 포스터 순서, 포스터 너비
		 * 
		 */
		int N = Integer.parseInt(br.readLine());
		List<Poster> posters = new ArrayList<>();	// 포스터들을 저장할 배열
		Set<Integer> pointSet = new HashSet<>();	// 좌표의 개수를 저장할 집합
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			posters.add(new Poster(start, end));
			pointSet.add(start);
			pointSet.add(end);
		}
		
		List<Integer> points = new ArrayList<>(pointSet);
		Collections.sort(points);
		
		// <좌표 값, 순서> 쌍으로 저장
		Map<Integer, Integer> compressedPoint = new HashMap<>();
		for (int i = 0; i < points.size(); i++) {
			compressedPoint.put(points.get(i), i);
		}
		
		// 벽에 포스터 붙임
		int[] wall = new int[pointSet.size()];
		for (int i = 0; i < N; i++) {
			Poster poster = posters.get(i);
			int start = compressedPoint.get(poster.start);
			int end = compressedPoint.get(poster.end);
			
			for (int j = start; j <= end; j++) {
				wall[j] = i + 1;
			}
		}
		
		// 볼 수 있는 포스터의개수 계산
		Set<Integer> visiblePosters = new HashSet<>();
		for (int i = 0; i < wall.length; i++) {
			if (wall[i] == 0) continue;
			
			visiblePosters.add(wall[i]);
		}
		
		System.out.println(visiblePosters.size());
	}
}
