import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 5개의 문자열을 입력 받을 2차원 배열을 선언
		char[][] arr = new char[5][15];
		// 제일 긴 문자열의 길이를 저장할 변수 max
		int max = 0;
		
		// 5개의 문자열을 입력받으면서 문자열을 문자로 쪼개 순서대로 저장
		for (int i = 0; i < 5; i++) {
			String str = sc.nextLine();
			for (int j = 0; j < str.length(); j++) {
				arr[i][j] = str.charAt(j);
			}
			if (max < str.length())
				max = str.length();
		}
		// char형은 디폴트값이 '\0'이다.
		// '\0'이면 출력하지 않고 다음 문자로 넘어가면서 문자열들을 세로로 출력
		for (int c = 0; c < max; c++) {
			for (int r = 0; r < 5; r++) {
				if (arr[r][c] == '\0') {
					System.out.print("");
				} else {
					System.out.print(arr[r][c]);
				}
			}
		}
	}
}