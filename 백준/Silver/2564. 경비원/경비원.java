import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int garo;	// 가로의 길이
	static int saero;	// 세로의 길이
	static int N;	// 상점의 개수
	static int[][] store;	// 상점의 방향 & 위치를 저장할 배열
	static int[][] DK = new int[1][2];	// 동건이 위치
	static int minLength = 0;	// 최단거리의 합
	
	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		garo = Integer.parseInt(st.nextToken());
		saero = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(br.readLine());
		store = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken());	// i번째 상점이 위치한 방향
			store[i][1] = Integer.parseInt(st.nextToken()); // i번째 상점이 위치한 칸
		}
		
		st = new StringTokenizer(br.readLine());
		DK[0][0] = Integer.parseInt(st.nextToken());	// 동근이가 위치한 방향
		DK[0][1] = Integer.parseInt(st.nextToken());	// 동근이가 위치한 칸
		
		for (int i = 0; i < N; i++) length(DK[0][0], DK[0][1], store[i][0], store[i][1]);
		
		System.out.println(minLength);
		
	}
	
	private static void length(int dongDirection, int dongBox, int sangDirection, int sangBox) {
		
		if (dongDirection == sangDirection) {
			minLength += Math.abs(sangBox - dongBox);
			return;
		}
		
		if (dongDirection == 1) {	// 동건이가 북에 위치할 때
			if (sangDirection == 3) {	// 상점이 서에 위치하는 경우
				minLength += sangBox + dongBox;
			} else if (sangDirection == 4) {	// 상점이 동에 위치하는 경우
				minLength += sangBox + (garo - dongBox);
			} else {	// 상점에 남에 위치하는 경우
				minLength += saero + Math.min(sangBox + dongBox, (garo - sangBox) + (garo - dongBox));
			}
			
		} else if (dongDirection == 2) {	// 동건이가 남에 위치할 때
			if (sangDirection == 1) {	// 상점에 북에 위치하는 경우
				minLength += saero + Math.min(sangBox + dongBox, (garo - sangBox) + (garo - dongBox));
			} else if (sangDirection == 3) {	// 상점이 서에 위치하는 경우
				minLength += (saero - sangBox) + dongBox;
			} else if (sangDirection == 4) {	// 상점이 동에 위치하는 경우
				minLength += (saero - sangBox) + (garo - dongBox);
			}
			
		} else if (dongDirection == 3) {	// 동건이가 서에 위치할 때
			if (sangDirection == 1) {	// 상점에 북에 위치하는 경우
				minLength += sangBox + dongBox;
			} else if (sangDirection == 4) {	// 상점이 동에 위치하는 경우
				minLength += garo + Math.min(sangBox + dongBox, (saero - sangBox) + (saero - dongBox));
			} else {	// 상점이 남에 위치하는 경우
				minLength += sangBox + (saero - dongBox);
			}
			
		} else {	// 동건이가 동에 위치할 때
			if (sangDirection == 1) {	// 상점에 북에 위치하는 경우
				minLength += (garo - sangBox) + dongBox;
			} else if (sangDirection == 3) {	// 상점이 서에 위치하는 경우
				minLength += garo + Math.min(sangBox + dongBox, (saero - sangBox) + (saero - dongBox));
			} else {	// 상점이 남에 위치하는 경우
				minLength += (garo - sangBox) + (saero - dongBox);
			}
		}
	}
	
	
}