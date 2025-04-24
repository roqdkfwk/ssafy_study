import java.util.*;
import java. io.*;
public class Main {

    static int N;
    static Map<String, Integer> nickMap;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nickMap = new HashMap<>();
        HashMap<Character, HashMap> map = new HashMap<>();
        N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            String str = br.readLine();

            addChar(map, str, 0, "", true);
        }

        System.out.println(answer.toString().trim());
    }

    private static void addChar(HashMap<Character, HashMap> map, String nickname, int index, String prefix, boolean flag) {
        if (index >= nickname.length()) {
            nickMap.put(prefix, nickMap.getOrDefault(prefix, 0) + 1);

            if (flag) {
                if (nickMap.get(prefix) == 1) {
                    answer.append(prefix).append("\n");
                } else {
                    answer.append(prefix).append(nickMap.get(prefix)).append("\n");
                }
            }
            return;
        }

        char ch = nickname.charAt(index);
        prefix += ch;
        if (!map.containsKey(nickname.charAt(index))) {
            map.put(ch, new HashMap<>());

            if (flag) {
                answer.append(prefix).append("\n");
                flag = false;
            }
        }

        HashMap<Character, HashMap> nextMap = map.get(ch);
        addChar(nextMap, nickname, index + 1, prefix, flag);
    }
}