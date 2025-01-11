import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[] spendTime;
    static int[] inDegree;
    static ArrayList<Integer>[] list;
    static int[] answer;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    private static void Solution() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                pq.add(i);
                answer[i] = spendTime[i];
            }
        }

        while (!pq.isEmpty()) {
            int currBuilding = pq.poll();

            for (int nextBuilding : list[currBuilding]) {
                inDegree[nextBuilding]--;

                answer[nextBuilding] = Math.max(answer[nextBuilding], answer[currBuilding] + spendTime[nextBuilding]);

                if (inDegree[nextBuilding] == 0) {
                    pq.add(nextBuilding);
                }
            }
        }
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        spendTime = new int[N + 1];
        inDegree = new int[N + 1];
        answer = new int[N + 1];

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            spendTime[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int building = Integer.parseInt(st.nextToken());
                if (building == -1) break;

                inDegree[i]++;
                list[building].add(i);
            }
        }
    }
}