import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		char[] ox = new char[80];
		
		for (int i = 0; i < T; i++) {
			String str = sc.next();
			for (int j = 0; j < str.length(); j++) {
				ox[j] = str.charAt(j);
			}
			int cnt = 0;
			int score = 0;
			
			for (int k = 0; k < str.length(); k++) {
				if (ox[k] == 'O') {
					cnt++;
					score += cnt;
				} else {
					cnt = 0;
				}
			}
			System.out.println(score);
		}		
	}
}