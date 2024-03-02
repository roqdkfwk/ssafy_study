import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R;	// 세로 길이
	static int C;	// 가로 길이, 좌표는 (C, R) = (x, y)
	static int x;	// 관객이 앉을 좌석의 x좌표
	static int y;	// 관객이 앉을 좌석의 y좌표
	static int rNow;	// 현재 위치한 행
	static int cNow;	// 현재 위치한 열
	static int K;	// 대기번호
	static int[][] number;	// 
	static int[] dr = {1, 0, -1, 0};	// 하, 우, 상, 좌
	static int[] dc = {0, 1, 0, -1};
	static int direction = 0;	// 방향을 정해줄 변수 direction
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		number = new int[R][C];
		
		K = Integer.parseInt(br.readLine());
		
		if (K > C * R) {
			System.out.println(0);
			return;
		}
		
		int seatNumber = 1;
		rNow = 0;
		cNow = 0;
		while (seatNumber <= C * R) {
			
			// 진행 방향을 바꾸는 경우
			// (제일 아래 줄이거나 아래 숫자가 0이 아닌데) && (진행 방향이 하 방향인 경우)
			if ((rNow == R - 1 || number[rNow + 1][cNow] != 0) && (direction % 4) == 0) {
				direction++;
			}
			// (제일 오른쪽 줄이거나 오른쪽 숫자가 0이 아닌데) && (진행 방향이 우 방향인 경우)
			else if ((cNow == C - 1 || number[rNow][cNow + 1] != 0) && (direction % 4) == 1) {
				direction++;
			}
			// (제일 위쪽 줄이거나 위 숫자가 0이 아닌데) && (진행 방향이 상 방향인 경우)
			else if ((rNow == 0 || number[rNow - 1][cNow] != 0) && (direction % 4) == 2) {
				direction++;
			}
			// (제일 왼쪽 줄이거나 왼쪽 숫자가 0이 아닌데) && (진행 방향이 좌 방향인 경우)
			else if ((cNow == 0 || number[rNow][cNow - 1] != 0) && (direction % 4) == 3) {
				direction++;
			}
			
			number[rNow][cNow] = seatNumber;
			
			// 해당 좌석에 배정된 번호가 관객의 대기번호와 같다면 x, y변수에 해당 좌석의 위치를 저장
			if (seatNumber == K) {
				x = cNow + 1;
				y = rNow + 1;
			}
			
			seatNumber++;
			rNow += dr[direction % 4];
			cNow += dc[direction % 4];
		}
		
		System.out.printf("%d %d", x, y);
	}
}