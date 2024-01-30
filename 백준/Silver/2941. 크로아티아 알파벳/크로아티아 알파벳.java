import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// BufferedReader를 이용해 받은 문자열을 str에 저장을하고
		String str = br.readLine();		
		
		// 문자열의 길이를 저장할 변수 len선언
		int len = str.length();
		
//		// 크로아티아 알파벳의 개수를 셀 변수 cnt
//		int cnt = 0;
		
		// 크로아티아 알파벳을 저장하는 배열
		String[] cro = {"c=", "c-", "d-", "lj", "nj", "s=", "z=", "dz="};
		
		if (str.contains(cro[7])) {
			str = str.replace(cro[7], "#");
		}
		for (int i = 0; i < 7; i++) {
			if (str.contains(cro[i])) {
				str = str.replace(cro[i], "#");
			}
		}
		System.out.println(str.length());
//		이렇게 하면 크로아티아 알파벳이 중복으로 있는 경우도 한 번만 세는듯?
//		// 길이가 2인 크로아티아 알파벳
//		for (int i = 0; i < 7; i++) {
//			// 입력받은 문자열이 길이가 2인 크로아티아 알파벳을 포함하고 있으면
//			if (str.contains(cro[i])) {
//				// cnt++를 통해 크로아티아 알파벳의 개수를 하나 추가하고
//				// 크로아티아 알파벳의 길이 만큼 2를 지워줌
//				cnt++;
//				len -= 2;
//			}			
//		}
//		// 길이가 3인 크로아티아 알파벳
//		if (str.contains(cro[7])) {
//			// cnt++를 통해 크로아티아 알파벳의 개수를 하나 추가하고
//			cnt++;
//			// 길이 3만큼 지워줌
//			len -= 3;
//		}
//		System.out.println(len + cnt);
	}
}