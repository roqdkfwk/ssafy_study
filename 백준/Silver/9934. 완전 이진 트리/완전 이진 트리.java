import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	// 이 문제는 그림을 그려봐야함. 완전 이진 트리의 특징 파악하기
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int K = Integer.parseInt(br.readLine());
        int N = 1;
        for (int i = 0; i < K; i++) N *= 2;
        N--;
        
        st = new StringTokenizer(br.readLine());
        int[] building = new int[N + 1];
        for (int i = 1; i < N + 1; i++) building[i] = Integer.parseInt(st.nextToken());
        
        int n = 1;
        int startIdx = (N + 1) / 2;
        for (int i = 0; i < K; i++) {
            
            int cnt = 1;
            int plusIdx = startIdx * 2;
            int idx = startIdx;
            while (cnt <= n) {
                System.out.print(building[idx] + " ");
                idx += plusIdx;
                cnt++;
            }
            
            startIdx /= 2;
            n *= 2;
            System.out.println();
        }
    }
}