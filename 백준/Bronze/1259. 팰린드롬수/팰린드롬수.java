import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
		
			boolean flag = true;
			
			String str = br.readLine();
			if (str.equals("0")) break;
			else {
				for (int i = 0; i < str.length() / 2; i++) if (str.charAt(i) != str.charAt(str.length() - 1 - i)) flag = false;
			}
			
			if (flag == true) System.out.println("yes");
			else System.out.println("no");
		}
	}
}