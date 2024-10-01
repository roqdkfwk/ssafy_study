// import java.util.*;
// import java.io.*;

// class Solution {
    
//     int answer;
//     boolean[] prime;
//     boolean[] visit;
//     char[] paper;
//     String numbers;
//     StringBuilder sb;
    
//     public int solution(String numbers) {
//         this.numbers = numbers;
//         answer = 0;
//         sb = new StringBuilder();
        
//         // 1 ~ 10,000,000까지 소수 찾고 어따가 저장
//         this.prime = new boolean[10000000];
//         Arrays.fill(prime, true);
//         prime[0] = prime[1] = false;
//         for (int i = 4; i < 10000000; i += 2) {
//             prime[i] = false;
//         }
//         for (int i = 3; i * i < 10000000; i += 2) {
//             if (prime[i]) {
//                 for (int j = i + 1; i * j < 10000000; j++) {
//                     if (j % i == 0) {
//                         prime[j] = false;
//                     }
//                 }
//             }
//         }
        
//         // 종이 조각 조합해서 숫자를 만들고
//         this.paper = new char[numbers.length()];
//         paper = numbers.toCharArray();
        
//         // 해당 숫자가 배열에 존재하면 체크하고 개수 증가
//         visit = new boolean[numbers.length()];
//         dfs(0);
        
//         // 마지막 숫자가 짝수인 경우는 체크하지 않아야 시간을 줄일 수 있음
    
//         return answer;
//     }
    
//     void dfs(int cnt) {
//         if (cnt == numbers.length()) {
//             if (sb.length() == 0) {
//                 return;
//             }
//             int num = Integer.parseInt(sb.toString());
//             if (prime[num]) {
//                 prime[num] = false;
//                 answer++;
//             }
//             return;
//         }        
//         for (int i = 0; i < numbers.length(); i++) {
//             if (!visit[i]) {
//                 visit[i] = true;
//                 sb.append(paper[i]);
//                 dfs(cnt + 1);
//                 visit[i] = false;
//                 sb.setLength(sb.length() - 1);
//                 dfs(cnt + 1);
//             }
//         }
//     }
// }

import java.util.*;

class Solution {
    
    int answer;
    boolean[] prime;
    boolean[] visit;
    char[] paper;
    String numbers;
    StringBuilder sb;
    
    public int solution(String numbers) {
        this.numbers = numbers;
        answer = 0;
        sb = new StringBuilder();
        
        // 에라토스테네스의 체 최적화
        this.prime = new boolean[10000000];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i * i < 10000000; i++) {
            if (prime[i]) {
                for (int j = i * i; j < 10000000; j += i) {
                    prime[j] = false;
                }
            }
        }
        
        this.paper = numbers.toCharArray();
        visit = new boolean[numbers.length()];
        dfs(0);
    
        return answer;
    }
    
    void dfs(int cnt) {
        if (cnt > 0) {
            int num = Integer.parseInt(sb.toString());
            if (prime[num]) {
                prime[num] = false;
                answer++;
            }
        }
        
        if (cnt == numbers.length()) {
            return;
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                sb.append(paper[i]);
                dfs(cnt + 1);
                sb.setLength(sb.length() - 1);
                visit[i] = false;
            }
        }
    }
}


















