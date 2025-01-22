import java.util.*;
import java.io.*;

public class Main {

    static int T, K;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InputHandler(br);

        printResult();
    }

    private static void printResult() {
        System.out.println(sb.toString().trim());
    }

    private static void Solution(BufferedReader br, int operators) throws IOException {
        StringTokenizer st;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < operators; i++) {
            st = new StringTokenizer(br.readLine());

            String oper = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (oper.equals("I")) {
                treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
            } else {
               if (treeMap.isEmpty()) continue;

               if (num == 1) {
                   int key = treeMap.lastKey();

                   if (treeMap.get(key) == 1) {
                       treeMap.remove(key);
                   } else {
                       treeMap.put(key, treeMap.get(key) - 1);
                   }
               } else {
                   int key = treeMap.firstKey();

                   if (treeMap.get(key) == 1) {
                       treeMap.remove(key);
                   } else {
                       treeMap.put(key, treeMap.get(key) - 1);
                   }
               }
            }
        }

        if (treeMap.isEmpty()) {
            sb.append("EMPTY").append("\n");
        } else {
            sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
        }
    }

    private static void InputHandler(BufferedReader br) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            K = Integer.parseInt(br.readLine());
            Solution(br, K);
        }
    }
}