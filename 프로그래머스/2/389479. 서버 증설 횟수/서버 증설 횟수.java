import java.util.*;

class Solution {
    /*
    딱 m명인 경우 증설하지 않아도 된다.
    n * m명 이상인 경우 n대의 서버를 증설해야 한다.
    */
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] runningServer = new int[24];  // 현재 돌아가는 서버의 수
        
        for (int time = 0; time < players.length; time++) {
            int quotient = players[time] / m;
            
            // 서버를 증설할 필요가 없는 경우
            if (quotient < 1 || quotient <= runningServer[time]) {
                continue;
            }
            
            // 추가적으로 필요한 서버의 수
            int additionalServer = quotient - runningServer[time];
            for (int i = time; i < time + k && i < players.length; i++) {
                runningServer[i] += additionalServer;
            }
            
            answer += additionalServer;
        }   
        
        return answer;
    }
}