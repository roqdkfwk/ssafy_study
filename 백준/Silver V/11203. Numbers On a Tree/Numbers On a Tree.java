import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		if (st.countTokens() > 1) {
			int H = Integer.parseInt(st.nextToken());
			long N = 1;
			for (int i = 0; i <= H; i++) N *= 2;
			N--;

			String oper = st.nextToken();

			long index = 0;
			for (int n = 0; n < oper.length(); n++) {
				char ch = oper.charAt(n);
				index = order(index, ch);
			}

			System.out.print(N - index);
		} else {
			int H = Integer.parseInt(st.nextToken());
			long N = 1;
			for (int i = 0; i <= H; i++) N *= 2;
			N--;

			System.out.println(N);
		}
	}

	static long order(long index, char ch) {

		if (ch == 'L') return index * 2 + 1;
		else return (index + 1) * 2;
	}
}