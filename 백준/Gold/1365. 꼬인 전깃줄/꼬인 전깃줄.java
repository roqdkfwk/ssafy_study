import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N + 1];
        
        // 증가 수열의 마지막 위치 = 꼬이지 않고 이을 수 있는 전선의 개수
        int index = 1;
        answer[1] = sequence[1];
        for (int i = 2; i <= N; i++) {
            int keyValue = sequence[i];     // 다음 값

            // 다음 값이 증가 수열의 마지막 값보다 큰 경우
            if (keyValue > answer[index]) {
                answer[++index] = keyValue;
            }
            // 다음 값이 증가 수열의 마지막 값보다 작은 경우
            // 이분 탐색을 사용해서 keyValue가 들어갈 위치를 찾는다.
            else {
                int left = 0;
                int right = index;
                while (left < right) {
                    int mid = (left + right) / 2;

                    if (keyValue > answer[mid]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                answer[left] = keyValue;
            }
        }
        
        // 총 전선의 개수(N) - 꼬이지 않고 이을 수 있는 전선의 개수(index)
        System.out.println(N - index);
    }
}