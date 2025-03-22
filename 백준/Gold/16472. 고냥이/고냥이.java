import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int left = 0;
        int right = 0;
        int answer = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < str.length()) {
            char key = str.charAt(right);
            map.put(key, map.getOrDefault(key, 0) + 1);
            right++;

            while (map.size() > N) {
                char leftChar = str.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                left++;
            }
            answer = Math.max(answer, right - left);
        }

        System.out.println(answer);
    }
}