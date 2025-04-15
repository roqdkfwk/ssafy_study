import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static Map<String, String> findParent;
    static Map<String, HashSet<String>> network;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            int F = Integer.parseInt(br.readLine());
            String[][] friendShip = new String[F][2];
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                friendShip[i][0] = st.nextToken();
                friendShip[i][1] = st.nextToken();
            }

            solution(F, friendShip);
        }
    }

    private static void solution(int F, String[][] friendShip) {
        findParent = new HashMap<>();
        network = new HashMap<>();

        for (int i = 0; i < F; i++) {
            if (network.get(friendShip[i][0]) == null) {
                network.put(friendShip[i][0], new HashSet<>());
                network.get(friendShip[i][0]).add(friendShip[i][0]);
            }
            if (network.get(friendShip[i][1]) == null) {
                network.put(friendShip[i][1], new HashSet<>());
                network.get(friendShip[i][1]).add(friendShip[i][1]);
            }

            String parent1 = findParent.getOrDefault(friendShip[i][0], friendShip[i][0]);
            String parent2 = findParent.getOrDefault(friendShip[i][1], friendShip[i][1]);

            String parent = parent1;
            if (!parent1.equals(parent2)) {
                parent = union(parent1, parent2);
            }

            sb.append(network.get(parent).size()).append("\n");
        }
    }

    private static String union(String p1, String p2) {
        String[] people = new String[2];
        people[0] = p1;
        people[1] = p2;
        Arrays.sort(people);

        // p2의 부모를 p1으로 바꿔주고 p2의 네트워크에 포함되어 있던 사람들을 p1의 네트워크에 포함시킴
        findParent.put(people[1], people[0]);
        network.get(people[0]).addAll(network.get(people[1]));

        // p2를 부모로 갖는 사람들의 부모를 p1으로 바꿈
        for (String person : network.get(people[1])) {
            findParent.put(person, people[0]);
        }
        network.remove(people[1]);
        return people[0];
    }

    private static void printResult() {
        System.out.println(sb.toString().trim());
    }
}