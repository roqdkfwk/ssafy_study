import java.util.*;
class Solution {
    
    int[][] points;
    boolean[][][] visited;
    Set<String> set;        // 충돌 시각, 위치를 저장할 집합
    
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        
        int answer = 0;
        
        visited = new boolean[15000][100][100];
        set = new HashSet<>();
        for (int i = 0; i < routes.length; i++) {
            moveRobots(routes[i]);
        }
        
        return set.size();
    }
    
    private void moveRobots(int[] route) {
        int time = 0;
        
        for (int i = 0; i < route.length - 1; i++) {
            int start = route[i] - 1;
            int end = route[i + 1] - 1;
            
            int r1 = points[start][0] - 1;
            int c1 = points[start][1] - 1;
            int r2 = points[end][0] - 1;
            int c2 = points[end][1] - 1;
            
            if (i == 0) {
                if (visited[time][r1][c1]) {
                    set.add(time + "," + r1 + "," + c1);
                }
                visited[time][r1][c1] = true;
            }

            while (r1 > r2) {
                r1--;
                time++;

                if (visited[time][r1][c1]) {
                    set.add(time + "," + r1 + "," + c1);
                }
                visited[time][r1][c1] = true;
            }

            while (r1 < r2) {
                r1++;
                time++;

                if (visited[time][r1][c1]) {
                    set.add(time + "," + r1 + "," + c1);
                }
                visited[time][r1][c1] = true;
            }

            while (c1 > c2) {
                c1--;
                time++;

                if (visited[time][r1][c1]) {
                    set.add(time + "," + r1 + "," + c1);
                }
                visited[time][r1][c1] = true;
            }

            while (c1 < c2) {
                c1++;
                time++;

                if (visited[time][r1][c1]) {
                    set.add(time + "," + r1 + "," + c1);
                }
                visited[time][r1][c1] = true;
            }
        }
    }
}























