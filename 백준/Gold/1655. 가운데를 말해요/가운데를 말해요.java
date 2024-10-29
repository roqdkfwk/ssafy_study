import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static PriorityQueue<Integer> pre, post;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        pre = new PriorityQueue<>(Comparator.reverseOrder());
        post = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {   // 짝수일 땐 pre
                pre.add(Integer.parseInt(br.readLine()));
            } else {            // 홀수일 땐 post
                post.add(Integer.parseInt(br.readLine()));
            }

            if (!pre.isEmpty() && !post.isEmpty() && pre.peek() > post.peek()) {
                int tmp = pre.poll();
                pre.add(post.poll());
                post.add(tmp);
            }

            sb.append(pre.peek()).append("\n");
        }

        System.out.println(sb);
    }
}