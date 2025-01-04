import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리를 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 플레이어들의 카드 번호를 저장할 배열
        int[] cards = new int[N];
        // 점수를 저장할 배열 (최종 결과값)
        int[] scores = new int[N];
        
        // 각 숫자의 인덱스를 저장할 배열 (1부터 1,000,000까지)
        int[] numToIdx = new int[1000001];
        Arrays.fill(numToIdx, -1);
        
        // 카드 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            numToIdx[cards[i]] = i;  // 각 숫자의 위치 저장
        }
        
        // 각 카드에 대해 배수 관계 확인
        for (int i = 0; i < N; i++) {
            int currentNum = cards[i];
            
            // currentNum의 배수들을 확인
            for (int multiple = currentNum * 2; multiple <= 1000000; multiple += currentNum) {
                // 해당 배수가 다른 플레이어의 카드라면
                if (numToIdx[multiple] != -1) {
                    // 현재 플레이어는 승리(+1), 배수를 가진 플레이어는 패배(-1)
                    scores[i]++;
                    scores[numToIdx[multiple]]--;
                }
            }
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int score : scores) {
            sb.append(score).append(" ");
        }
        System.out.println(sb);
    }
}