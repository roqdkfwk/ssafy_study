import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	// 주사위의 개수
	static int[][] dice;	// 주사위
	static int maxSum;	// 숫자의 합들 중 최댓값을 저장할 변수
	static int sum;	// 숫자의 합을 저장할 변수
	static int topside;	// 위쪽 방향에 써 있는 숫자
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) dice[i][j] = Integer.parseInt(st.nextToken());			
		}	// i에 대한 for문		
		
		for (int i = 0; i < N; i++) {	// index기준 2, 3번째 숫자 자리 바꾸기, 마주보는 순서쌍을 (1, 2), (3, 4), (5, 0)으로 만들어주기 위해서
			int tmp = dice[i][2];
			dice[i][2] = dice[i][3];
			dice[i][3] = tmp;
		}

		maxSum = 0;
		for (int idx = 0; idx < N; idx++) {
			
			sum = 0;	// 케이스마다 sum을 0으로 초기화
			findMaxSum(0, idx);	// 0번째 주사위의 idx번째 면이 아래에 위치하는 경우
			if (maxSum < sum) maxSum = sum;
		}
		
		System.out.println(maxSum);
	}
	
	// 옆면에 쓰인 숫자들의 합의 최댓값을 구하는 메소드
	static void findMaxSum(int diceNumber, int downSideIndex) {	// findMaxSum(주사위 순서, 아랫면 인덱스)
		
		if (diceNumber == N - 1) {	// 마지막 주사위를 입력받은 경우 다음 주사위는 살피지 않고 옆면의 숫자 중 최댓값만 더함
			summationSideNumber(diceNumber, downSideIndex);
			return;
		}
		
		if (downSideIndex == 0) {	// 아랫면의 index가 0인 경우
			topside = dice[diceNumber][5];	// 윗면의 index는 5
			summationSideNumber(diceNumber, 0);	// 해당 주사위의 옆면의 값 중 가장 큰 값을 sum에 대함
			
			for (int idx = 0; idx < 6; idx++) {
				if (dice[diceNumber + 1][idx] == topside) {	// 아래 주사위의 윗면과 대응되는 윗 주사위의 아랫면 index를 찾아서
					findMaxSum(diceNumber + 1, idx);	// findMaxSum메소드에 주사위 번호, 아랫면 index를 대입
					return;
				}
			}
			
		} else if (downSideIndex == 1) {
			topside = dice[diceNumber][2];
			summationSideNumber(diceNumber, 1);

			for (int idx = 0; idx < 6; idx++) {
				if (dice[diceNumber + 1][idx] == topside) {	
					findMaxSum(diceNumber + 1, idx);
					return;
				}
			}			
			
		} else if (downSideIndex == 2) {
			topside = dice[diceNumber][1];
			summationSideNumber(diceNumber, 2);
			
			for (int idx = 0; idx < 6; idx++) {
				if (dice[diceNumber + 1][idx] == topside) {	
					findMaxSum(diceNumber + 1, idx);
					return;
				}
			}
			
		} else if (downSideIndex == 3) {
			topside = dice[diceNumber][4];
			summationSideNumber(diceNumber, 3);
			
			for (int idx = 0; idx < 6; idx++) {
				if (dice[diceNumber + 1][idx] == topside) {	
					findMaxSum(diceNumber + 1, idx);
					return;
				}
			}
			
		} else if (downSideIndex == 4) {
			topside = dice[diceNumber][3];
			summationSideNumber(diceNumber, 4);
			
			for (int idx = 0; idx < 6; idx++) {
				if (dice[diceNumber + 1][idx] == topside) {	
					findMaxSum(diceNumber + 1, idx);
					return;
				}
			}
			
		} else {
			topside = dice[diceNumber][0];
			summationSideNumber(diceNumber, 5);
			
			for (int idx = 0; idx < 6; idx++) {
				if (dice[diceNumber + 1][idx] == topside) {	
					findMaxSum(diceNumber + 1, idx);
					return;
				}
			}
		}
	}
	
	// 옆면에 쓰인 숫자 중 가장 큰 값을 찾아 더해주는 메소드
	static void summationSideNumber(int diceNumber, int downIndex) {
		
		int maxSide = 0;
		if (downIndex == 0) {
			for (int idx = 1; idx < 5; idx++) {
				if (maxSide < dice[diceNumber][idx]) maxSide = dice[diceNumber][idx];
			}
			
			sum += maxSide;	// 옆면에 위치한 숫자 중 제일 큰 값을 sum에 더함
			return;
			
		} else if (downIndex == 1) {
			for (int idx = 3; idx < 7; idx++) {
				if (maxSide < dice[diceNumber][(idx % 6)]) maxSide = dice[diceNumber][(idx % 6)];	// idx를 나머지처리 안하면 배열 범위를 벗어남
			}
			
			sum += maxSide;
			return;
			
		} else if (downIndex == 2) {
			for (int idx = 3; idx < 7; idx++) {
				if (maxSide < dice[diceNumber][(idx % 6)]) maxSide = dice[diceNumber][(idx % 6)];
			}
			
			sum += maxSide;
			return;
			
		} else if (downIndex == 3) {
			for (int idx = 5; idx < 9; idx++) {
				if (maxSide < dice[diceNumber][idx % 6]) maxSide = dice[diceNumber][idx % 6];
			}
			
			sum += maxSide;
			return;
			
		} else if (downIndex == 4) {
			for (int idx = 5; idx < 9; idx++) {
				if (maxSide < dice[diceNumber][(idx % 6)]) maxSide = dice[diceNumber][idx % 6];
			}
			
			sum += maxSide;
			return;
			
		} else {
			for (int idx = 1; idx < 5; idx++) {
				if (maxSide < dice[diceNumber][(idx % 6)]) maxSide = dice[diceNumber][idx % 6];
			}
			
			sum += maxSide;
			return;
		}
	}
}