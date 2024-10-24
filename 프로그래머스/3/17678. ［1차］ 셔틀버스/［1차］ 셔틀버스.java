import java.util.*;
class Solution {
    
    int[] time, prefix;
    String answer = "";
    int conTime;
    int maxCrew;
    int lastShuttle;
    
    // 셔틀은 09:00부터
    public String solution(int n, int t, int m, String[] timetable) {
        time = new int[1441];   // 00:00 ~ 23:59까지
        prefix = new int[1441];
        this.maxCrew = n * m;
        
        int lastTime = 0;
        for (int i = 0; i < timetable.length; i++) {
            time[timeToMinute(timetable[i])]++;
            lastTime = Math.max(lastTime, timeToMinute(timetable[i]));
        }
        
        // i분까지 도착하는 크루원의 수를 누적
        // shuttleIndex = 지나간 셔틀의 수
        int shuttleIndex = 0;
        for (int i = 1; i <= lastTime; i++) {
            prefix[i] = prefix[i - 1] + time[i];
            
            // 현재 남은 크루원의 수 >= 앞으로 탑승할 수 있는 크루원의 수
            if (prefix[i] >= (n - shuttleIndex) * m) {
                conTime = i - 1;
                answer = String.format("%02d:%02d", conTime / 60, conTime % 60);
                return answer;
            }
            
            // 셔틀이 도착할 때마다 크루원이 탑승
            if (shuttleIndex < n && i == 540 + shuttleIndex * t) {
                prefix[i] = Math.max(0, prefix[i] - m);
                shuttleIndex++;
            }
        }
        
        conTime = 540 + (n - 1) * t;
        answer = String.format("%02d:%02d", conTime / 60, conTime % 60);
        return answer;
    }
    
    int timeToMinute(String time) {
        String[] minute = time.split(":");
        
        return Integer.parseInt(minute[0]) * 60
            + Integer.parseInt(minute[1]);
    }
}