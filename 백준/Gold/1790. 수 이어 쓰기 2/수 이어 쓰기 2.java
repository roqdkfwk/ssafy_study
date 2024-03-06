import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long length;	// 수열의 길이
	static long N2;
	static int exp;	// 자리수
	static long k;
	static int kexp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Long.parseLong(st.nextToken());
		
		N2 = N;
		exp = 1;
		while (N2 / 10 != 0) {	// N의 자리수 계산
			N2 /= 10;
			exp++;
		}
		
		for (int i = 1; i < exp; i++) length += i * (9 * Math.pow(10, i - 1));
		// ex) N == 523이면 3자리수가 424번 나오는 것이므로
		// 3 * (523 - 100 + 1)개
		length += exp * ((N - Math.pow(10, exp - 1)) + 1);
		
		if (k > length) System.out.println(-1);
		else {
			
			kexp = 1;
			while (k > kexp * (9 * Math.pow(10, kexp - 1))) {
				k -= kexp * (9 * Math.pow(10, kexp - 1));
				kexp++;
			}
			
			int q = (int)(k / kexp);
			int r = (int)(k % kexp);
			
//			System.out.println("q : " + q + " r : " + r);
			
			String num = "";
			if (kexp == 1) num = String.valueOf(q);
			else {
				if (r == 0) num = String.valueOf((int)Math.pow(10, kexp - 1) + q - 1);
				else num = String.valueOf((int)Math.pow(10, kexp - 1) + q);
			}
			
//			System.out.println(num);
			
			if (r == 0) System.out.println(num.charAt(kexp - 1));
			else System.out.println(num.charAt(r - 1));
			
		}
	}
}