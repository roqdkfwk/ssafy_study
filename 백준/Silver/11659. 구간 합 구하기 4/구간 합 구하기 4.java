import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());
		// 누적합을 저장할 배열 stackSum
		int[] stackSum = new int[N];
		// 배열의 첫 번째 자리에 처음으로 받는 숫자를 저장하고
		stackSum[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			// index 1에 해당하는 두 번째 자리부터는 앞의 값들과 입력받은 숫자의 합(누적합)을 저장
			stackSum[i] = stackSum[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		for (int T = 0; T < M; T++) {
			// 누적합을 저장할 변수 sum
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			// stackSum은 누적합을 저장할 배열이므로
			// 원래 배열의 3번째부터 5번째 숫자들의 합은
			// stackSum배열에서 index 4의 값에서 index 1의 값을 뺸 것과 같다
			if (i == 1) {
				System.out.println(stackSum[j - 1]);
			} else {
				System.out.println(stackSum[j - 1] - stackSum[i - 2]);
			}
		}
			
			
//		int[] numArr = new int[N];
//		for (int i = 0; i < N; i++) {
//			numArr[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		for (int T = 0; T < M; T++) {
//			int sum = 0;
//			st = new StringTokenizer(br.readLine());
//			int i = Integer.parseInt(st.nextToken());
//			int j = Integer.parseInt(st.nextToken());
//			for (int A = i - 1; A < j; A++) {
//				sum += numArr[A];
//			}
//			System.out.println(sum);
//		}	
	}
}