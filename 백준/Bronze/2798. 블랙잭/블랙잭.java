import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 카드
		int N = sc.nextInt();
		// 한도 숫자
		int M = sc.nextInt();
		// 카드의 숫자를 저장할 배열
		int[] card = new int[N];
		// 한도 숫자를 넘지 않는 선에서 만들 수 있는 숫자들의 합의 최댓값
		int max = 0;
		// 세 카드에 적힌 숫자들 저장할 변수
		int sum = 0;
				
		for (int i = 0; i < N; i++) {
			card[i] = sc.nextInt();
		}
		// 숫자들을 오름차순으로 정렬
		Arrays.sort(card);
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				for (int k = j + 1; k < N; k++) {
					// 세 카드에 적힌 숫자의 합을 저장하고
					sum = card[i] + card[j] + card[k];
					// 합이 max값보다 크지만 M 이하인 경우 max에 sum값을 대입
					if (sum > max && sum <= M) {
						max = sum;
					}
				}
			}
		}
		System.out.println(max);
	}
}