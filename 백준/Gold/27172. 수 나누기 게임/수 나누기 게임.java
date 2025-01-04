import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int maxNum;
    static int[] players;
    static HashMap<Integer, Integer> cards;
    static HashMap<Integer, List<Integer>> winnerMap, loserMap;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printReesult();
    }

    private static void printReesult() {
        System.out.println(sb.toString().trim());
    }

    private static void Solution() {
        /*
        * 완전탐색은 시간초과
        * 핵심로직
        * - 1번이 2번한테 이기고, 2번이 3번한테 이기면 1번은 3번한테 이긴다.
        */
        winnerMap = new HashMap<>();
        loserMap = new HashMap<>();
        for (int card : cards.keySet()) {
            int num = card;
            while (num <= maxNum) {
                num += card;

                if (!cards.containsKey(num)) continue;

                List<Integer> win = winnerMap.getOrDefault(card, new ArrayList<>());
                win.add(num);
                winnerMap.put(card, win);

                List<Integer> lose = loserMap.getOrDefault(num, new ArrayList<>());
                lose.add(card);
                loserMap.put(num, lose);
            }
        }

        sb = new StringBuilder();
        for (int player : players) {
            int score = winnerMap.getOrDefault(player, new ArrayList<>()).size()
                        - loserMap.getOrDefault(player, new ArrayList<>()).size();
            sb.append(score).append(" ");
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        players = new int[N];

        cards = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());
            players[i] = key;
            cards.put(key, 1);
            maxNum = Math.max(maxNum, key);
        }
    }
}