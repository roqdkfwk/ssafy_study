import java.util.*;
import java.io.*;
public class Main {

    static class Group implements Comparable<Group> {
        int totalFriends, totalCandies;

        public Group(int totalFriends, int totalCandies) {
            this.totalFriends = totalFriends;
            this.totalCandies = totalCandies;
        }

        @Override
        public int compareTo(Group g) {
            if (this.totalFriends == g.totalFriends) {
                return Integer.compare(g.totalCandies, this.totalCandies);
            }
            return Integer.compare(g.totalFriends, this.totalFriends);
        }
    }

    static int N, M, K;
    static int[] candies;
    static List<List<Integer>> friendShip;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 아이들 수
        M = Integer.parseInt(st.nextToken());   // 친구 관계 수
        K = Integer.parseInt(st.nextToken());   // 최소 아이 수

        st = new StringTokenizer(br.readLine());
        candies = new int[N];
        for (int i = 0; i < N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        friendShip = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            friendShip.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            friendShip.get(A).add(B);
            friendShip.get(B).add(A);
        }
    }

    /**
     * 같은 그룹에 포함된 친구의 수, 해당 그룹의 사탕 수를 구해서
     * K명 미만의 인원이 포함되도록 그룹을 조합해서 최대의 사탕 수를 찾는다.
     */
    private static void solution() {
        boolean[] visited = new boolean[N];
        List<Group> groupList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            Group group = new Group(0, 0);
            visited[i] = true;
            queue.add(i);
            while (!queue.isEmpty()) {
                int child = queue.poll();

                group.totalFriends++;
                group.totalCandies += candies[child];

                for (int next : friendShip.get(child)) {
                    if (visited[next]) continue;

                    visited[next] = true;
                    queue.add(next);
                }
            }

            if (group.totalFriends < K) {
                groupList.add(group);
            }
        }

        answer = combinationGroup(groupList);
    }

    private static int combinationGroup(List<Group> groupList) {
        int[] dp = new int[K];

        for (Group group : groupList) {
            int friends = group.totalFriends;
            int candies = group.totalCandies;

            for (int i = K - 1; i >= friends; i--) {
                dp[i] = Math.max(dp[i], dp[i - friends] + candies);
            }
        }

        for (int val : dp) {
            answer = Math.max(answer, val);
        }

        return answer;
    }

    private static void printResult() {
        System.out.println(answer);
    }
}