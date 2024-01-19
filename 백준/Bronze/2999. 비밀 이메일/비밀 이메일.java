import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String mes = sc.nextLine();
		int N = mes.length();
		// N의 약수의 개수를 저장할 변수
		int cnt = 0;
		int[] div = new int[N + 1];
		int R = 0;
		int C = 0;
		
		// N의 약수 중에서 (N / 2)보다 큰 것은 없으므로
		// N까지 반복하지 않아도 모든 약수를 구할 수 있다
		for (int i = 1; i <= (N / 2); i++) {
			if (N % i == 0) {
				div[i - 1] = i;
			}				
		}
		// N의 약수 중 자기자신인 N을 뺐으므로 정확한 약수의 개수는 cnt + 1이다.
		// 0과 자기자신 제외하고 약수들을 모두 찾기 위해 배열을 오름차순으로 정렬
		Arrays.sort(div);
		for (int i = 0; i <= N; i++) {
			if (div[i] != 0)
				cnt++;
		}
		// N의 약수들을 저장할 배열
		int[] divArr = new int[cnt + 1];
		divArr[cnt] = N;
		for (int i = 0; i < cnt; i++) {
			divArr[i] = div[N - cnt + 1 + i];
		}
		
		if (cnt % 2 != 0) {
			R = divArr[(cnt / 2)];
			C = divArr[(cnt / 2) + 1];
		} else {
			R = divArr[cnt / 2];
			C = R;
		}
		char[][] decode = new char[R][C];
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				decode[j][i] = mes.charAt(R * i + j);
			}			
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(decode[i][j]);
			}
		}
	}
}