import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] A;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(A);

        for (int targetIdx = 0; targetIdx < N; targetIdx++) {
        	// 라벨링
        	out:
            for (int now = 0; now < N; now++) {
                if (targetIdx == now) continue;

                int num = A[targetIdx] - A[now];			  // 필요한 나머지 수
                int idx = lowerBoundBinarySearch(A, num);     // num의 가장 작은 인덱스 탐색

                if (idx >= 0 && idx < N && A[idx] == num) {
                    while (idx < N && A[idx] == num) {
                        if (idx != targetIdx && idx != now) {
                            answer++;
                            break out;
                        }
                        idx++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    /**
     * 같은 숫자 중에서도 가장 작은 인덱스를 찾는 lower bound 방식의 binary search
     * - target 이상인 첫 번째 인덱스를 반환
     * - 정확히 target이 존재하지 않으면, target보다 큰 첫 위치를 반환
     */
    private static int lowerBoundBinarySearch(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;

        while (left < right) {
            int mid = (left + right) >> 1;

            if (A[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left == A.length ? -1 : left;
    }
}
