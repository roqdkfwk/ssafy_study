import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[] locations;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 트랙의 길이
        M = Integer.parseInt(st.nextToken());   // 심판
        K = Integer.parseInt(st.nextToken());   // 심판의 위치

        st = new StringTokenizer(br.readLine());
        locations = new int[K];
        for (int i = 0; i < K; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }

        if (M == 1) {
            answer.append("1");
            for (int i = 1; i < K; i++)
                answer.append("0");

            System.out.println(answer.toString().trim());
            return;
        }

        if (M == 2) {
            answer.append("1");
            for (int i = 1; i < K - 1; i++)
                answer.append("0");
            answer.append("1");

            System.out.println(answer.toString().trim());
            return;
        }

        // 가장 가까운 심판의 거리가 최대
        // 거리를 기준으로 이분탐색
        // 심판 M명을 다 수용할 수 있는 거리 중 가장 먼 거리 >> upper bound
        int left = 0;
        int right = N + 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int curr = locations[0];
        int count = 1;
        answer.append("1");
        for (int index = 1; index < K; index++) {
            if (count >= M) {
                answer.append("0");
                continue;
            }
            if (locations[index] - curr >= left - 1) {
                answer.append("1");
                curr = locations[index];
                count++;
            } else {
                answer.append("0");
            }
        }

        System.out.println(answer.toString().trim());
    }

    private static boolean isValid(int length) {
        int index = 1;
        int count = 1;
        int curr = locations[0];

        // length 이상의 간격으로 심판들을 배치 했을때 배치할 수 있는 심판의 숫자
        while (index < K) {
            if ((locations[index] - curr) >= length) {
                curr = locations[index];
                count++;
                index++;
            } else {
                index++;
            }
        }
        return count >= M;
    }
}