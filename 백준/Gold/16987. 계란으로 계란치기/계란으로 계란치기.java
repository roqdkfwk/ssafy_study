import java.util.*;
import java.io.*;
public class Main {

	static int N;
	static int[][] eggs;	// eggs[내구도][무게]
	static int answer;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}
	
	static void printResult() {System.out.println(answer);}
	
	static void breakEgg(int start, int[][] eggList ,boolean[] brokenEgg) {
		// 제일 오른쪽 계란까지 모두 고려한 경우 총 깨진 계란의 수를 계산하고 종료
		if (start == N) {
			int count = 0;
			for (int i = 0; i < N; i++)
				if (brokenEgg[i]) count++;
			
			answer = Math.max(answer, count);
			return;
		}
		
		// 손에 든 계란이 깨진 경우 다음 계란으로 넘어간다.
		if (brokenEgg[start]) {
			breakEgg(start + 1, eggList, brokenEgg);
			return;
		}
		
		// 모든 계란이 깨진 경우 종료한다.
		boolean allBroken = true;
	    for (int i = 0; i < N; i++) {
	        if (i != start && !brokenEgg[i]) {
	            allBroken = false;
	            break;
	        }
	    }
	    
	    if (allBroken) {
	        breakEgg(start + 1, eggList, brokenEgg);
	        return;
	    }
		
		/*
		 * Solution() 메서드에서 eggs를 인자로 넘길 때는 eggs 배열의 주소 값이 전달된다.
		 * 그 상태에서 E = eggList로 초기화를 하는 경우 E는 eggs의 주소 값을 참조하기 때문에
		 * E의 값을 변경하면 기존의 eggs의 값 또한 변경되므로 깊은 복사를 거쳐야 한다.
		 */
		int[][] E = new int[N][2];
		for (int i = 0; i < N; i++) {
			E[i][0] = eggList[i][0];
			E[i][1] = eggList[i][1];
		}
		
		boolean[] broken = new boolean[N];
		for (int i = 0; i < N; i++)
			broken[i] = brokenEgg[i];
		
		for (int i = 0; i < N; i++) {
			// 손에 들고 있는 계란을 고려하는 경우 || 깨진 계란을 고려하는 경우
			if (i == start || broken[i]) continue;
			
			// 계란의 상태를 저장한다.
			int eggState1 = E[start][0];
			int eggState2 = E[i][0];
			
			if (!broken[start] && !broken[i]) {
				// 손에 들고 있는 계란의 내구도 = Math.max(0, 현재 내구도 - 다음 계란의 무게)
				E[start][0] = Math.max(0, E[start][0] - E[i][1]);
				if (E[start][0] == 0)
					broken[start] = true;
				
				// 다음 계란의 내구도 = Math.max(0, 현재 내구도 - 손에 들고 있는 계란의 무게)
				E[i][0] = Math.max(0, E[i][0] - E[start][1]);
				if (E[i][0] == 0)
					broken[i] = true;
			}
			breakEgg(start + 1, E, broken);
			
			// 계란의 상태를 원상복구한다.
			E[start][0] = eggState1;
			E[i][0] = eggState2;
			broken[start] = false;
			broken[i] = false;
		}
	}
	
	static void Solution() {
		breakEgg(0, eggs, new boolean[N]);
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		eggs = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}
	}
}