import java.io.*;
import java.util.*;

public class Main {
	
	static char[][] grid;
	static Queue<int[]> puyo;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}

	static void printResult() {
		System.out.println(answer);
	}
	
	static void gravity() {
		for (int r = 10; r >= 0; r--) {
			for (int c = 0; c < 6; c++) {
				if (grid[r][c] == '.') continue;
				
				int nr = r;
				while (nr + 1 < 12 && grid[nr + 1][c] == '.') nr++;
				
				if (nr == r) continue;
				
				grid[nr][c] = grid[r][c];
				grid[r][c] = '.';
			}
		}
	}
	
	static void removePuyo(Queue<int[]> py) {
		while(!py.isEmpty()) {
			int[] curr = py.poll();
			grid[curr[0]][curr[1]] = '.';
		}
	}
	
	static boolean isValid(int r, int c, char color) {
		return r >= 0 && r < 12 && c >= 0 && c < 6 && grid[r][c] == color; 
	}
	
	static boolean bfs(int[] location) {
		char color = grid[location[0]][location[1]];
		puyo.add(location);
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(location);
		visited[location[0]][location[1]] = true;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				
				if (!isValid(nr, nc, color)) continue;
				if (visited[nr][nc]) continue;
				
				puyo.add(new int[] {nr, nc});
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		return puyo.size() >= 4;
	}
	
	static void Solution() {
		boolean isChain;     
        do {
            isChain = false;
            visited = new boolean[12][6];
            
            // 전체 필드를 검사하면서 터질 수 있는 뿌요들을 모두 찾음
            for (int r = 11; r >= 0; r--) {
                for (int c = 0; c < 6; c++) {
                	if (grid[r][c] == '.') continue;
                	if (visited[r][c]) continue;
                	
                	puyo = new LinkedList<>();
                    if (bfs(new int[] {r, c})) {
                    	removePuyo(puyo);
                    	isChain = true;
                    }
                    else puyo.clear();
                }
            }
            
            if (isChain) {  // 연쇄가 발생했다면
                gravity();  // 중력 적용
                answer++;   // 연쇄 횟수 증가
            }
        } while (isChain);  // 더 이상 터질 수 있는 뿌요가 없을 때까지 반복
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		grid = new char[12][6];
		for (int r = 0; r < 12; r++) {
			grid[r] = br.readLine().toCharArray();
		}
	}
}