import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> truck = new LinkedList<Integer>();
		Queue<Integer> bridge = new LinkedList<Integer>();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
//		트럭 수
		int n = Integer.parseInt(st.nextToken());
//		다리의 길이 = 트럭 한 대가 다리를 건너는데 걸리는 시간
		int w = Integer.parseInt(st.nextToken());
		for (int i = 0; i < w; i++)
			bridge.add(0);
//		다리의 최대 하중
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			truck.add(Integer.parseInt(st.nextToken()));

//		다리 위에 올라가있는 트럭들의 무게의 합
		int weight = 0;
		int time = 0;
		while (!bridge.isEmpty()) {

//			다리에서 내려오는 트럭의 무게를 빼준다.
			weight -= bridge.poll();
			time++;
			
			if (!truck.isEmpty()) {
//				다리 위의 트럭과 기다리는 트럭의 무게의 합이 최대 중량보다 작거나 같다면
				if (weight + truck.peek() <= L) {
//					다리 위에 트럭을 올려준다.
					weight += truck.peek();
					bridge.add(truck.poll());
				} else
					bridge.add(0);
			}
		}
		
		System.out.println(time);
	}
}