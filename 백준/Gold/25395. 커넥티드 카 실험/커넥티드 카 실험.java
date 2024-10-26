import java.util.*;
import java.io.*;

public class Main {
	
	static class Car {
		int num, pos, fuel;
		
		public Car (int n, int p, int f) {
			this.num = n;
			this.pos = p;
			this.fuel = f;
		}
	}

	static int N, S;
	static int[] pos, fuel;
	static boolean[] visit;
	static Queue<Car> queue;
	static List<Car> cars;
	static List<Integer> answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		pos = new int[N + 1];
		fuel = new int[N + 1];
		visit = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1 ; i <= N; i++) {
			pos[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			fuel[i] = Integer.parseInt(st.nextToken());
		}
		
		cars = new ArrayList<>();
		cars.add(null);
		for (int i = 1; i <= N; i++) {
			cars.add(new Car(i, pos[i], fuel[i]));
		}
		
		queue = new LinkedList<>();
		queue.add(cars.get(S));		// 시작하는 커넥티드카
		
		answer = new ArrayList<Integer>();
		answer.add(cars.get(S).num);
		visit[cars.get(S).num] = true;
		
		while (!queue.isEmpty()) {	// bfs
			Car curr = queue.poll();
			
			searchLeft(curr);		// 좌로 탐색
			searchRight(curr);		// 우로 탐색
		}
		
		answer.sort(null);
		for (int num : answer) {
			sb.append(num).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void searchLeft(Car car) {
        int index = car.num;
        
        if (index == 1) { // 가장 왼쪽 차량이면 더 이상 탐색할 필요 없음
            return;
        }
        
        for (int i = index - 1; i > 0; i--) {
        	int distance = cars.get(index).pos - cars.get(i).pos;
        	
        	if (cars.get(index).fuel < distance)
        		break;
        	
        	if (!visit[i]) {
        		visit[i] = true;
        		answer.add(i);
        		queue.add(cars.get(i));
        	}
        }
    }
    
    static void searchRight(Car car) {
        int index = car.num;
        
        if (index == N) { // 가장 오른쪽 차량이면 더 이상 탐색할 필요 없음
            return;
        }
        
        for (int i = index + 1; i <= N; i++) {
        	int distance = cars.get(i).pos - cars.get(index).pos;
        	
        	if (cars.get(index).fuel < distance) 
        		break;
        	
        	if (!visit[i]) {
        		visit[i] = true;
        		answer.add(i);
        		queue.add(cars.get(i));
        	}
        }
    }
}