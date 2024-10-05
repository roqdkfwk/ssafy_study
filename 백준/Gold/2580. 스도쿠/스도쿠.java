import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static int[][] grid;
    static List<int[]> zeros;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        grid = new int[9][9];
        zeros = new ArrayList<>();
        
        for (int r = 0; r < 9; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 9; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
                if (grid[r][c] == 0) {
                    zeros.add(new int[]{r, c});
                }
            }
        }
        
        dfs(0);
        printGrid();
    }
    
    static boolean dfs(int index) {
        if (index == zeros.size()) {
            return true; // 모든 빈 칸을 채웠으면 true 반환
        }
        
        int[] pos = zeros.get(index);
        int row = pos[0];
        int col = pos[1];
        
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                grid[row][col] = num;
                if (dfs(index + 1)) {
                    return true;
                }
                grid[row][col] = 0; // 백트래킹
            }
        }
        return false;
    }
    
    static boolean isValid(int row, int col, int num) {
        // 행 검사
        for (int c = 0; c < 9; c++) {
            if (grid[row][c] == num) return false;
        }
        
        // 열 검사
        for (int r = 0; r < 9; r++) {
            if (grid[r][col] == num) return false;
        }
        
        // 3x3 사각형 검사
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (grid[r][c] == num) return false;
            }
        }
        
        return true;
    }
    
    static void printGrid() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }
}