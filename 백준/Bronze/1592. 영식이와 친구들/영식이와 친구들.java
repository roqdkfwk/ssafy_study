import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 게임에 참가한 사람 수
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		// N명의 참가자가 공을 받은 횟수를 저장할 배열
		int[] ball = new int[N];
		// 첫 번째 사람은 공을 받고 시작하므로 값을 1로 초기화
		ball[0] = 1;
		// 볼의 위치를 나타낼 변수 idx
		int idx = 0;
		// 볼이 돈 횟수를 나타낼 변수 cnt
		int cnt = 0;
		
		// 볼을 받은 횟수가 M번인 사람이 나올 때까지 놀이를 게속함
		while (true) {
			// 볼을 받은 횟수가 M인 경우 놀이를 멈춘다
			if (ball[idx] == M)
				break;
			
			// 볼을 받은 횟수가 짝수번이면 반시계 방향으로
			if (ball[idx] % 2 == 0) {
				idx = (idx + N - L) % N;
				ball[idx]++;
			}
			// 볼을 받은 횟수가 홀수번이면 시계 방향으로
			else if (ball[idx] % 2 != 0) {
				idx = (idx + L) % N;
				ball[idx]++;
			}
			cnt++;
		}
		System.out.println(cnt);
	}
}