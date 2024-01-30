import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 창고를 격자형태로 1001 X 1001으로 쪼갠 후 0으로 초기화
		int[][] area = new int[1001][1001];
		
		// 기둥의 개수를 입력받고
		int N = sc.nextInt();
		
		// 기둥의 정보를 입력받을 배열 선언
		// arr[위치][높이]
		int[][] arr = new int[N][2];
		
		// 높이가 가장 높은 기둥의 위치와 높이를 저장할 변수
		int maxHeightIdx = 0;
		int maxHeight = 0;
		
		// 넓이
		int surface = 0;
		
		// 위치와 넓이를 입력받고
		for (int i = 0; i < N; i++) {
			// 위치
			arr[i][0] = sc.nextInt();
			// 높이
			arr[i][1] = sc.nextInt();
		}
		
		// 높이가 가장 높은 기둥의 위치와 높이를 찾음
		for (int i = 0; i < N; i++) {
			if (arr[i][1] > maxHeight) {
				maxHeight = arr[i][1];
				maxHeightIdx = arr[i][0];
			}
		}

		for (int i = 0; i < N; i++) {
			// 기둥이 왼쪽에 있는 경우
			// 해당 기둥에서 제일 높은 기둥까지 1로 채움
			if (arr[i][0] <= maxHeightIdx) {
				for (int j = arr[i][0]; j <= maxHeightIdx; j++) {
					for (int k = 0; k < arr[i][1]; k++) {
						area[j][k] = 1;
					}
				}
				// 기둥이 오른 쪽에 있는 경우
				// 높이가 가장 높은 기둥에서 해당 기둥까지 1로 채움
			} else {
				for (int j = maxHeightIdx; j <= arr[i][0]; j++) {
					for (int k = 0; k < arr[i][1]; k++) {
						area[j][k] = 1;
					}
				}
			}
		}
		for (int i = 0; i < maxHeight; i++) {
			area[maxHeightIdx][i] = 1;
		}
		
		// 채워진 1의 개수 = 넓이
		for (int i = 0; i < 1001; i++) {
			for (int j = 0; j < 1001; j++) {
				if (area[i][j] == 1)
					surface++;
			}
		}
		// 넓이를 출력
		System.out.println(surface);
	}
}