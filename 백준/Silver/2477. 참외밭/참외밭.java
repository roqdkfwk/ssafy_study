import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;	// 단위 면적에서 자라는 참외의 수
	static int rowMax = 0;	// 세로 길이의 최대
	static int colMax = 0;	// 가로 길이의 최대
	static int[][] size;	// [방향][길이]를 저장할 배열
	static int surface;	// 참외밭의 면적
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		size = new int[6][2];
		for (int i = 0; i < 6; i++) {
			
			st = new StringTokenizer(br.readLine());
			size[i][0] = Integer.parseInt(st.nextToken());	// 방향
			size[i][1] = Integer.parseInt(st.nextToken());	// 길이
			
			if (size[i][0] <= 2) {	// 동, 서쪽이면
				if (rowMax < size[i][1]) rowMax = size[i][1];
			} else {
				if (colMax < size[i][1]) colMax = size[i][1];
			}		
		}	// i에 대한 for문
		
		surface = rowMax * colMax;	// 최대넓이
		
		for (int i = 0; i < 6; i++) {
			
			if (size[i % 6][0] == size[(i + 2) % 6][0] && size[(i + 1) % 6][0] == size[(i + 3) % 6][0]) 
				surface -= size[(i + 1) % 6][1] * size[(i + 2) % 6][1];
		}
		
		System.out.println(surface * N);
	}
}