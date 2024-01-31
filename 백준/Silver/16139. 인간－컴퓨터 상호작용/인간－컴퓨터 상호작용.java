import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 문자열을 입력받고
		String str = br.readLine();
		// 입력받은 문자열을 문자열 배열에 char타입으로 순서대로 저장
		char[] strArr = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			strArr[i] = str.charAt(i);
		}
		// 반복할 횟수를 입력받는다.
		int N = Integer.parseInt(br.readLine());
		
		for (int T = 0; T < N; T++) {
			// 찾고자하는 알파벳이 나오는 횟수
			int cnt = 0;
			// 찾고자하는 알파벳, 찾고자하는 범위를 입력받는다.
			st = new StringTokenizer(br.readLine());
			char alpha = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			// 입력받은 범위를 순회하며 찾고자하는 알파벳의 개수를 센다.
			for (int i = l; i < r + 1; i++) {
				if (strArr[i] == alpha) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}