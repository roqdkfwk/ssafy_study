import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] arrQS = new int[2][N];
		for (int r = 0; r < 2; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = N - 1; c >= 0; c--) arrQS[r][c] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int c = 0; c < N; c++) {
			if (arrQS[0][c] == 0) queue.add(arrQS[1][c]);
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)	queue.add(Integer.parseInt(st.nextToken()));
		
		for (int i = 0; i < M; i++) sb.append(queue.poll()).append(" ");

		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}