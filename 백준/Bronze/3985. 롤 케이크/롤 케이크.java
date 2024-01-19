import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 케이크의 길이
		int L = sc.nextInt();
		// 케이크의 상태를 저장할 배열
		int[] cake = new int[L];
		// 방청객의 수
		int N = sc.nextInt();
		// 각 방청객이 종이에 적어낸 수를 저장할 배열
		int[][] person = new int[N][2];
		// 방청객별 받은 케이크의 수를 저장할 배열
		int[] takeCake = new int[N];
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				person[i][j] = sc.nextInt();
			}
		}
		// N명의 방청객이 케이크 조각을 가져가는 것을 반대로 설정
		// 왜냐하면 먼저 받는 사람은 뒤에 받는 사람에게 영향을 받지 않아야 하니까
		for (int i = N-1; i >= 0; i--) {
			for (int j = person[i][0] - 1; j < person[i][1]; j++) {
				// (i + 1)번째 사람이 가져간 케이크조각에 (i + 1)표시를 해줌
				cake[j] = i + 1;
			}
		}
		// 종이에 적어낸 수의 차이가 가장 큰 값을 저장할 변수
		int maxdiff = 0;
		int perNum = 0;
		for (int i = 0; i < N; i++) {
			if (person[i][1] - person[i][0] > maxdiff) {
				maxdiff = person[i][1] - person[i][0];
				perNum = i + 1;
			}
		}
		// 가장 많은 조각을 받을 것으로 예상되는 방청객의 번호를 출력
		System.out.println(perNum);

		// 방청객별로 가져간 케이크의 개수를 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < L; j++) {
				if (cake[j] == i) {
					takeCake[i - 1] += 1;
				}
			}
		}
		int maxCake = 0;
		// 가장 많은 케이크를 가져간 사람의 번호
		int perMax = 0;
		for (int i = 0; i < N; i++) {
			if (maxCake < takeCake[i]) {
				maxCake = takeCake[i];
				perMax = i + 1;
			}
		}
		System.out.println(perMax);
	}
}