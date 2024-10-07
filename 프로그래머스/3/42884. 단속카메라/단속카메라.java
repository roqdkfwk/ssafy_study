import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < routes.length; i++) {
            queue.add(new int[] {routes[i][0], routes[i][1]});
        }
        // for (int i = 0; i < routes.length; i++) {
        //     System.out.println(queue.peek()[0] + " " + queue.poll()[1]);
        // }
        
        // 진출 시점보다 진입 시점이 앞서는 모든 차량을 한 번에 처리한다.
        while (!queue.isEmpty()) {
            int out = queue.poll()[1];
            while (!queue.isEmpty() && queue.peek()[0] <= out) {
                queue.poll();
            }
            
            answer++;
        }
        
        return answer;
    }
}