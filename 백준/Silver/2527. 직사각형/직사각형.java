import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] XY;	// 직사각형의 꼭짓점의 좌표를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int n = 0; n < 4; n++) {
			
			st = new StringTokenizer(br.readLine());
			XY = new int[4][2];
			for (int i = 0; i < 4; i++) {
				XY[i][0] = Integer.parseInt(st.nextToken());
				XY[i][1] = Integer.parseInt(st.nextToken());
			}
			
			if (XY[2][0] > XY[1][0] || XY[2][1] > XY[1][1] || XY[3][0] < XY[0][0] || XY[3][1] < XY[0][1]) {
				System.out.println("d");
				
			} else if ((XY[0][0] == XY[3][0] && XY[1][1] == XY[2][1]) || (XY[0][0] == XY[3][0] && XY[0][1] == XY[3][1])
					|| (XY[1][0] == XY[2][0] && XY[0][1] == XY[3][1]) || (XY[1][0] == XY[2][0] && XY[1][1] == XY[2][1])) {
				System.out.println("c");
				
			} else if (XY[1][0] == XY[2][0] || XY[1][1] == XY[2][1] || XY[3][0] == XY[0][0] || XY[0][1] == XY[3][1]) {
				System.out.println("b");
				
			} else {
				System.out.println("a");
			}
		}
	}
}