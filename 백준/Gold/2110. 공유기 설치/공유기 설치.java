import java.util.*;
import java.io.*;
public class Main {

    static int N, C;
    static List<Integer> houses;
    static int firstHouse, lastHouse;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    /**
     * 인접한 두 공유기 사이의 최대 거리 >> upperbound
     */
    private static void solution() {
        int left = 1;
        int right = lastHouse + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // (가지고 있는 공유기의 수) > (설치할 수 있는 공유기의 수)인 경우
            // 공유기를 설치하는 간격을 좁혀야 하므로 upperbound를 당겨온다.
            if (C > countRouter(mid)) {
                right = mid;
            }
            // 반대의 경우 공유기를 설치하는 간격을 최대한 넓혀야 하므로 lowerbound를 당겨온다.
            else {
                left = mid + 1;
            }
        }
        answer = left - 1;
    }

    private static int countRouter(int distance) {
        int lastRouter = houses.get(0);
        int installedRouter = 1;
        for (int location : houses) {
            if (location - lastRouter < distance) continue;

            lastRouter = location;
            installedRouter++;
        }
        return installedRouter;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new ArrayList<>();
        firstHouse = 1_000_000_001;
        lastHouse = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            houses.add(num);

            firstHouse = Math.min(firstHouse, num);
            lastHouse = Math.max(lastHouse, num);
        }
        Collections.sort(houses);
    }
}