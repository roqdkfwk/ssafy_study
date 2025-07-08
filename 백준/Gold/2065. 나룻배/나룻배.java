import java.util.*;
import java.io.*;

public class Main {

    static class Guest implements Comparable<Guest> {
        int num;
        int arrivedAt;
        String departure;

        public Guest(int num, int arrivedAt, String departure) {
            this.num = num;
            this.arrivedAt = arrivedAt;
            this.departure = departure;
        }

        @Override
        public int compareTo(Guest g) {
            return Integer.compare(this.arrivedAt, g.arrivedAt);
        }
    }

    static int M, t, N;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 정원
        t = Integer.parseInt(st.nextToken()); // 이동 시간
        N = Integer.parseInt(st.nextToken()); // 손님 수
        answer = new int[N];

        PriorityQueue<Guest> left = new PriorityQueue<>();
        PriorityQueue<Guest> right = new PriorityQueue<>();
        int index = 0;
        for (int i = 0; i < N; i++) {
            String[] guest = br.readLine().split(" ");
            if (guest[1].equals("left")) {
                left.add(new Guest(index, Integer.parseInt(guest[0]), guest[1]));
            } else {
                right.add(new Guest(index, Integer.parseInt(guest[0]), guest[1]));
            }
            
            index++;
        }

        int currentTime = 0;
        String currentLocation = "left";
        Deque<Guest> boat = new ArrayDeque<>();

        while (!left.isEmpty() || !right.isEmpty() || !boat.isEmpty()) {
            // 현재 선착장 기준 탑승 가능한 손님 태우기
            int passengers = 0;
            if (currentLocation.equals("left")) {
                while (!left.isEmpty() && left.peek().arrivedAt <= currentTime && passengers < M) {
                    boat.add(left.poll());
                    passengers++;
                }
            } else {
                while (!right.isEmpty() && right.peek().arrivedAt <= currentTime && passengers < M) {
                    boat.add(right.poll());
                    passengers++;
                }
            }

            if (!boat.isEmpty()) {
                // boat에 탄 사람들 도착 시간 기록
                for (Guest guest : boat) {
                    answer[guest.num] = currentTime + t;
                }
                
                boat.clear();
                currentTime += t;
                currentLocation = currentLocation.equals("left") ? "right" : "left";
            } else {
                // 현재 시각 이후에 도착할 손님이 있는지 체크하고 대기 또는 이동
                int nextLeft = left.isEmpty() ? Integer.MAX_VALUE : left.peek().arrivedAt;
                int nextRight = right.isEmpty() ? Integer.MAX_VALUE : right.peek().arrivedAt;
                int nextTime = Math.min(nextLeft, nextRight);

                if (nextTime > currentTime) {
                    currentTime = nextTime;
                } else {
                    currentTime += t;
                    currentLocation = currentLocation.equals("left") ? "right" : "left";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int time : answer) {
            sb.append(time).append("\n");
        }

        System.out.print(sb);
    }
}