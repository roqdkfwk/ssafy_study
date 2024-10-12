import java.util.*;
import java.io.*;
public class Main {

	static int T, M;
	static int[] arr;
	static List<Integer> list, mid;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			M = Integer.parseInt(br.readLine());
			int count = 0;
			
			arr = new int[M];
			list = new ArrayList<Integer>();
			mid = new ArrayList<Integer>();
			st = null;
			for (int i = 0; i < M; i++) {
				if (i % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }
				
				arr[i] = Integer.parseInt(st.nextToken());
				list.add(arr[i]);
				
				if (i % 2 == 0) {	// 홀수 번째 수를 읽는 경우
					count++;		// 중앙값의 개수를 1만큼 증가시킨다.
					list.sort(null);
					mid.add(list.get(list.size() / 2));
				}
			}
			
			sb.append(count).append("\n");
            for (int i = 0; i < mid.size(); i++) {
                sb.append(mid.get(i)).append(" ");
                if ((i + 1) % 10 == 0 && i < mid.size() - 1) {
                    sb.append("\n");
                }
            }
            sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
