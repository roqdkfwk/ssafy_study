import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = 0;
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int[] alpha = new int[26];
			
			boolean flag = true;
			String str = br.readLine();
			alpha[str.charAt(0) - 'a']++;
			for (int j = 1; j < str.length(); j++) {
				if (alpha[str.charAt(j) - 'a'] != 0 && (str.charAt(j - 1) != str.charAt(j))) {
					flag = false;
					break;
				}
				alpha[str.charAt(j) - 'a']++;
			}
			
			if (flag == true) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}