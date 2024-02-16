import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 처음으로 0이 아닌 숫자가 나오는 위치 = 숫자가 인수로 가지는 10의 개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int hasTwo = 0;
		int hasFive = 0;
		
		for (int i = 2; i <= N; i++) {
			
			int num = i;
			// num가 2의 배수인 경우
			if (num % 2 == 0) {
				// i에 2의 인수가 몇 개가 있는지 센다.
				while (i >= 2 && num % 2 == 0) {
					hasTwo++;
					num /= 2;
				}
			// 위와 마찬가지 논리로 5가 인수로 몇 개나 있는지 센다.
			} 
			if (num % 5 == 0) {
				while (num >= 5 && num % 5 == 0) {
					hasFive++;
					num /= 5;
				}
			}
		}
		
		System.out.println(Math.min(hasTwo, hasFive));
	}
}