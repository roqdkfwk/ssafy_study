import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine();
		char[] wordChar = new char[word.length()];
		for (int i = 0; i < wordChar.length; i++) {
			wordChar[i] = word.charAt(i);
		}
		
		String keyWord = br.readLine();
		char[] keyWordChar = new char[keyWord.length()];
		for (int i = 0; i < keyWordChar.length; i++) {
			keyWordChar[i] = keyWord.charAt(i);
		}
		
		int cnt = 0;
		for (int i = 0; i < word.length() - keyWord.length() + 1; i++) {
			boolean flag = false;
			// keyWordChar은 항상 첫째 요소부터 조사해야하므로
			// idx변수를 따로 설정
			for (int j = i, idx = 0; j < i + keyWord.length(); j++) {
				if (wordChar[j] != keyWordChar[idx++]) {
					flag = true;
				}
			}
			if (flag == false) {
				cnt++;
				for (int j = i; j < i + keyWord.length(); j++) {
					wordChar[j] = '#';
				}
			}
		}
		System.out.println(cnt);
	}
}