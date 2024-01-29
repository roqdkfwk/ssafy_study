import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int xSize = Integer.parseInt(st.nextToken());
		int ySize = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int hour = Integer.parseInt(br.readLine()); 
		
		
		// 상하좌우 어느 방향으로 진행하는지 알려줄 변수 xDir, yDir
		int xDir = (hour - xSize + x) / xSize;
		int yDir = (hour - ySize + y) / ySize;
		
		// t시간 후 개미의 위치 좌표를 나타낼 변수
		int xNow = 0;
		int yNow = 0;
		
		if (hour - xSize + x <= 0)
			xNow = x + hour;
		else {
			if (xDir % 2 != 0) {
				xNow = (hour - xSize + x) % xSize; 
			} else {
				xNow = xSize - ((hour - xSize + x) % xSize);
			}
		}
		if (hour - ySize + y <= 0)
			yNow = y + hour;
		else {
			if (yDir % 2 != 0) {
				yNow = (hour - ySize + y) % ySize;
			} else {
				yNow = ySize - ((hour - ySize + y) % ySize); 
			}
		}
		System.out.printf("%d %d", xNow, yNow);
	}
}