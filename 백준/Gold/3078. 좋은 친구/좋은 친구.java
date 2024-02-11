import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer>[] nameLenArr = new Queue[21];
		for (int i = 0; i < 21; i++) nameLenArr[i] = new LinkedList<>();

		long cnt = 0;
		for (int i = 0; i < N; i++) {
			int len = br.readLine().length();

			if (nameLenArr[len].isEmpty()) {
				nameLenArr[len].add(i);
				continue;
			}

			while (i - nameLenArr[len].peek() > K) {
				nameLenArr[len].poll();
				if (nameLenArr[len].isEmpty()) {
					break;
				}
			}

			cnt += nameLenArr[len].size();
			nameLenArr[len].add(i);
		}

		System.out.println(cnt);
	}
}