import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	
	static class Tower {
		int Height, Low;
		
		public Tower (int Height, int Low) {
			this.Height = Height;
			this.Low = Low;
		}
	}
	
	static int N;
	static List<Tower> towers;
	static long answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		towers = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			towers.add(new Tower(Integer.parseInt(br.readLine()), 0));
		}
		
		Stack<Tower> stack = new Stack<>();
		stack.add(towers.get(N - 1));
		for (int i = N - 2; i >= 0; i--) {
			if (!stack.isEmpty() && stack.peek().Height < towers.get(i).Height) {
				while (!stack.isEmpty() && stack.peek().Height < towers.get(i).Height) {	// 다음 빌딩이 현재 스택의 peek 빌딩보다 높다면
					answer += stack.peek().Low;												// peek 빌딩이 확인할 수 있는 빌딩의 수를 answer에 더해주고
					towers.get(i).Low += stack.peek().Low + 1;								// 다음 빌딩은 peek 빌딩보다 한 개의 빌딩을 더 확인할 수 있으니 + 1해서 저장
					stack.pop();															// peek 빌딩을 스택에서 제거한다.
				}
			}
//			System.out.println("i : " + i + " " + towers.get(i).Low);			
			stack.add(towers.get(i));														// 어떤 경우든 다음 빌딩은 스택에 추가
		}
		
		while (!stack.isEmpty()) {
			answer += stack.pop().Low;
		}
		
		System.out.println(answer);
	}
}
