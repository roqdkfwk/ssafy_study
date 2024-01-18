import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] scoreArr = new int[10];
		// 합해진 점수의 개수를 저장할 변수
		int num = 0;
		// 누적합을 저장할 변수
		int[] stackSum = new int[10];
		
		for (int i = 0; i < 10; i++)
			scoreArr[i] = sc.nextInt();
		
		// 마리오가 받은 점수의 합이 100점보다 작은 동안 계속 진행
		while (num < 10) {
			// 누적합을 순서대로 stackSum 배열에 저장
			for (int idx = 0; idx <= num; idx++) {
				stackSum[num] += scoreArr[idx];
			}
			if (stackSum[num] > 100)
				break;
			num++;
		}
		
		if (num == 10) 
			System.out.println(stackSum[9]);
		else {
			// 100보다 작은 합과 큰 합의 평균이 100보다 큰 경우
			// 큰 합이 작은 합보다 100에서 더 멀리 떨어져 있는 것이므로
			// 작은 값을 출력
			if (stackSum[num - 1] + stackSum[num] > 200)
				System.out.println(stackSum[num - 1]);
			// 100보다 작은 합과 큰 합의 평균이 100보다 작거나 같은 경우
			// 큰 값을 출력
			else
				System.out.println(stackSum[num]);
		}
	}
}