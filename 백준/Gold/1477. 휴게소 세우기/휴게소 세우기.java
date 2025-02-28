import java.util.*;
import java.io.*;

public class Main {

    static int N, M, L;
    static int[] locations;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void printResult() {
        System.out.println(answer);
    }

    private static void solution() {
        int left = 1;
        int right = 1001;
        while (left < right) {
            int length = (left + right) / 2;

            if (M < haveToBuild(length)) {
                left = length + 1;
            } else {
                right = length;
            }
        }
        answer = left;
    }

    private static int haveToBuild(int length) {
        int builtArea = 0;  // 지어야하는 휴게소의 수
        int lastArea = 0;   // 마지막에 방문한 휴게소의 위치
        int index = 0;
        while (index < N) {
            if (locations[index] - lastArea <= length) {
                lastArea = locations[index];
                index++;
            } else {
                lastArea += length;
                builtArea++;
            }
        }
        while (lastArea + length < L) {
            lastArea += length;
            builtArea++;
        }
        return builtArea;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            locations = new int[N];
            for (int i = 0; i < N; i++) {
                locations[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(locations);
        } else {
            locations = new int[0];
        }
    }
}