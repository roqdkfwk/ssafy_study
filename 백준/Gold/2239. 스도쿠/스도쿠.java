import java.util.*;
import java.io.*;
public class Main {

    static int[][] sudoku;
    static boolean[][] rowCheck, colCheck, squareCheck;
    static List<int[]> zeroList;
    static boolean foundSudoku;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        rowCheck = new boolean[9][10];
        colCheck = new boolean[9][10];
        squareCheck = new boolean[9][10];
        zeroList = new ArrayList<>();

        for (int r = 0; r < 9; r++) {
            String str = br.readLine();
            for (int c = 0; c < 9; c++) {
                int num = str.charAt(c) - '0';
                sudoku[r][c] = num;

                if (num == 0) {
                    zeroList.add(new int[]{r, c});
                }
                else {
                    rowCheck[r][num] = true;
                    colCheck[c][num] = true;
                    squareCheck[(r / 3) * 3 + (c / 3)][num] = true;
                }
            }
        }
    }

    private static void solution() {
        dfs(0);
    }

    // 행, 열, 사각형 하나라도 num을 포함하고 있으면 안된다.
    private static boolean isValid(int r, int c, int num) {
        return !(rowCheck[r][num] || colCheck[c][num] || squareCheck[(r / 3) * 3 + (c / 3)][num]);
    }

    private static void dfs(int index) {
        if (foundSudoku) return;
        if (index == zeroList.size()) {
            foundSudoku = true;
            return;
        }

        int[] curr = zeroList.get(index);
        int r = curr[0];
        int c = curr[1];

        for (int num = 1; num <= 9; num++) {
            if (!isValid(r, c, num)) continue;

            sudoku[r][c] = num;
            rowCheck[r][num] = true;
            colCheck[c][num] = true;
            squareCheck[(r / 3) * 3 + (c / 3)][num] = true;

            dfs(index + 1);
            if (foundSudoku) return;

            sudoku[r][c] = 0;
            rowCheck[r][num] = false;
            colCheck[c][num] = false;
            squareCheck[(r / 3) * 3 + (c / 3)][num] = false;
        }
    }

    private static void printResult() {
        StringBuilder answer = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                answer.append(sudoku[r][c]);
            }
            answer.append("\n");
        }

        System.out.println(answer.toString().trim());
    }
}