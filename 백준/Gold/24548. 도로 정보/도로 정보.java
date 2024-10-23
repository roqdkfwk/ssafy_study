import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	static int N;
	static int[][] arr;
	static Map<String, Integer> map;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		// T, G, F, P
		arr = new int[4][N];
		if (str.charAt(0) == 'T') {
			arr[0][0]++;
		} else if (str.charAt(0) == 'G') {
			arr[1][0]++;
		} else if (str.charAt(0) == 'F') {
			arr[2][0]++;
		} else {
			arr[3][0]++;
		}
			
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				arr[j][i] = arr[j][i - 1];
			}
			
			if (str.charAt(i) == 'T') {
				arr[0][i] = (arr[0][i - 1] + 1) % 3;
			} else if (str.charAt(i) == 'G') {
				arr[1][i] = (arr[1][i - 1] + 1) % 3;
			} else if (str.charAt(i) == 'F') {
				arr[2][i] = (arr[2][i - 1] + 1) % 3;
			} else {
				arr[3][i] = (arr[3][i - 1] + 1) % 3;
			}
		}
		
		map = new HashMap<>();
		map.put("0000", 1);
		for (int i = 0; i < N; i++) {
			String key = "";
			for (int j = 0; j < 4; j++) {
				key += String.valueOf(arr[j][i]);
			}
			
			map.put(key, map.getOrDefault(key, 0) + 1);
		}
		
		for (int value : map.values()) {
			answer += value * (value - 1) / 2;
		}
		
		System.out.println(answer);
	}
}