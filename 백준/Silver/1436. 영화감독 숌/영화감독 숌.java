import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int count = 1;
	static int number = 666;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        
		while(count != N) {
			
			number++;
			if(String.valueOf(number).contains("666")) {
				count++;
			}
		}
		System.out.println(number);
	}
}