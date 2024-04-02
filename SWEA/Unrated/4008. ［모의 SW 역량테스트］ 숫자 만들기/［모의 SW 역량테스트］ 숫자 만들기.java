import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int T;    // 테스트 케이스의 개수
    static int N;    // 숫자의 개수
    static int[] num;    // 숫자를 저장할 배열
    static int[] afterNum; // 연산 후 나온 숫자를 저장할 배열
    static int[] oper;    // +, -, *, / 연산자의 사용 가능 횟수를 저장할 배열
    static int maxAns;    // 연산 결과의 최댓값
    static int minAns;    // 연산 결과의 최솟값
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " ");
            
            // 테스트 케이스마다 maxAns와 minAns를 초기화
            maxAns = Integer.MIN_VALUE;
            minAns = Integer.MAX_VALUE;
            
            N = Integer.parseInt(br.readLine());
            num = new int[N];
            afterNum = new int[N];    // 메모리를 줄일 수 있는 방법도 있을 것 같다
            
            oper = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) oper[i] = Integer.parseInt(st.nextToken());    // 연산자 사용 가능 횟수 저장
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
            afterNum[0] = num[0];    // afterNum[idx] = afterNum[idx - 1] (연산자) num[idx]

            
            for (int i = 0; i < 4; i++) {
                
                
                calculateNumber(i, 0, num[0]);
//                System.out.println(maxAns+" abc "+minAns);
            }
                
            
            sb.append(maxAns - minAns).append("\n");
        }    // t에 대한 for문
        
        System.out.println(sb);
    }    // main
    
    static void calculateNumber(int idx, int count) {    // 연산자 번호, 연산이 시행된 횟수
        System.out.println(count + ":" + Arrays.toString(afterNum));
        // 연산이 (숫자 개수 - 1)번 시행되었으면(연산이 끝났으면) 연산 결과를 저장 후 메소드를 종료
        if (count == N - 1) {
//            System.out.println("maxAns : " + maxAns);
            
            maxAns = Math.max(maxAns, afterNum[N - 1]);
            
//            System.out.println("minAns : " + minAns);
            
            minAns = Math.min(minAns, afterNum[N - 1]);
            
            System.out.println("max : " + maxAns + " min : " + minAns);
            return;
        }
        
//        System.out.println("count : " + count);
        
        // idx번에 해당하는 연산자를 모두 썼다면 다시 전단계로 돌아감
        if (oper[idx] == 0) return;
            
        if (idx == 0) {    // 더하기 연산자인 경우
            oper[idx]--;    // 연산자를 사용 가능한 횟수를 1만큼 감소
            afterNum[count + 1] = afterNum[count] + num[count + 1];
            for (int i = 0; i < 4; i++) calculateNumber(i, count + 1);
            
        } else if (idx == 1) {    // 빼기 연산자인 경우
            oper[idx]--;    // 연산자를 사용 가능한 횟수를 1만큼 감소
            afterNum[count + 1] = afterNum[count] - num[count + 1];
            for (int i = 0; i < 4; i++) calculateNumber(i, count + 1);
            
        } else if (idx == 2) {    // 곱하기 연산자인 경우
            oper[idx]--;    // 연산자를 사용 가능한 횟수를 1만큼 감소
            afterNum[count + 1] = afterNum[count] * num[count + 1];
            for (int i = 0; i < 4; i++) calculateNumber(i, count + 1);
            
        } else {
            oper[idx]--;    // 연산자를 사용 가능한 횟수를 1만큼 감소
            afterNum[count + 1] = afterNum[count] / num[count + 1];
            for (int i = 0; i < 4; i++) calculateNumber(i, count + 1);
        }
    }    // calculateNumber

    static void calculateNumber(int idx, int count, int result) {    // 연산자 번호, 연산이 시행된 횟수
        if (count == N - 1) {
            maxAns = Math.max(maxAns, result);
            minAns = Math.min(minAns, result);
            return;
        }
        
        
        if (oper[idx] == 0) return;
            
        if (idx == 0) {    // 더하기 연산자인 경우
            for (int i = 0; i < 4; i++) {
                oper[idx]--;
                calculateNumber(i, count + 1, result + num[count + 1]);
                oper[idx]++;
            }
        } else if (idx == 1) {    // 빼기 연산자인 경우
            for (int i = 0; i < 4; i++) {
                oper[idx]--;
                calculateNumber(i, count + 1, result - num[count + 1]);
                oper[idx]++;
            }
        } else if (idx == 2) {    // 곱하기 연산자인 경우
            for (int i = 0; i < 4; i++) {
                oper[idx]--;
                calculateNumber(i, count + 1, result * num[count + 1]);
                oper[idx]++;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                oper[idx]--;
                calculateNumber(i, count + 1, result / num[count + 1]);
                oper[idx]++;
            }
        }
    }
    
}