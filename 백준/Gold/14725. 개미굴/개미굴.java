import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 먹이 정보를 사전 순으로 출력하기 위해서 TreeMap 사용
        TreeMap<String, TreeMap> root = new TreeMap<>();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] foods = br.readLine().split(" ");
            
            addRoot(root, foods, 1);
        }

        for (String food : root.keySet()) {
            printRoot(root, food, 0);
        }

        System.out.println(sb.toString().trim());
    }

    // addRoot(층 정보, 먹이 정보, 다음 먹이의 index)
    private static void addRoot(TreeMap<String, TreeMap> food, String[] foods, int index) {
        if (index >= foods.length) return;

        if (!food.containsKey(foods[index])) {
            food.put(foods[index], new TreeMap<>());
        }
        addRoot(food.get(foods[index]), foods, index + 1);
    }

    private static void printRoot(TreeMap<String, TreeMap> food, String str, int depth) {
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(str).append("\n");

        TreeMap<String, TreeMap> nextFoods = food.get(str);
        if (nextFoods == null || nextFoods.isEmpty()) return;

        for (String nextFood : nextFoods.keySet()) {
            printRoot(nextFoods, nextFood, depth + 1);
        }
    }
}