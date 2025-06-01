import java.util.*;
import java.io.*;
public class Main {
	
	static class Trie {
		HashMap<Character, Trie> map = new HashMap<>();
		boolean isEnd = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			Map<Character, Trie> nums = new HashMap<>();
			int N = Integer.parseInt(br.readLine());
			boolean consistence = true;
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				
				// 일관성 확인
				if (availableNum(str, nums)) continue;
				consistence = false;
			}
			
			if (consistence) answer.append("YES").append("\n");
			else answer.append("NO").append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static boolean availableNum(String num, Map<Character, Trie> nums) {
		boolean result = true;
		for (int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);
			
			if (nums.get(ch) == null) {
				nums.put(ch, new Trie());
				
				// 마지막 숫자인 경우 isEnd를 true
				if (i == num.length() - 1) {
					nums.get(ch).isEnd = true;
				}
			} else {
				if (nums.get(ch).isEnd) {
					result = false;
				}
                
                if (i == num.length() - 1) {
					result = false;
				}
			}
			
			// 다음 문자를 탐색하기 위해서 nums에 새로운 map을 할당
			nums = nums.get(ch).map;
		}
		return result;
	}
}