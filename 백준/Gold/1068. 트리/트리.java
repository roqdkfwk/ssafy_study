import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer>[] child = new ArrayList[N];
        for (int i = 0; i < N; i++) child[i] = new ArrayList<>();

        // 자식 노드추가
        int root = -1;
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) root = i;
            else child[p].add(i);
        }

        // 노드 삭제
        int del = Integer.parseInt(br.readLine());
        if (del == root) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(root);

        while (!dq.isEmpty()) {
            int curr = dq.pop();
            int childNode = 0;

            for (int next : child[curr]) {
                if (next == del) continue;
                childNode++;
                dq.add(next);
            }
            
            // 자식 노드가 하나도 없었다면 리프노드
            if (childNode == 0) answer++;
        }
        System.out.println(answer);
    }
}