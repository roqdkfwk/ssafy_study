import java.io.*;
import java.util.*;

public class Main {

    static int W, N;
    static Map<String, Boolean> dictionary;  // 단어 사전
    static char[][][] boards;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};  // 좌상부터 시계방향
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};  // 좌상부터 시계방향
    static int maxLen;
    static String maxStr;
    static int count;
    static Set<String> foundWords;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());
        dictionary = new HashMap<>();
        for (int i = 0; i < W; i++) {
            String word = br.readLine();
            dictionary.put(word, true);
        }

        br.readLine();

        N = Integer.parseInt(br.readLine());
        boards = new char[N][4][4];
        for (int n = 0; n < N; n++) {
            for (int i = 0; i < 4; i++) {
                String str = br.readLine();
                for (int j = 0; j < 4; j++) {
                    boards[n][i][j] = str.charAt(j);
                }
            }
            if (n < N - 1) {
                br.readLine();
            }
        }
    }

    private static void solution() {
        for (int n = 0; n < N; n++) {
            maxLen = 0;
            maxStr = "";
            count = 0;
            foundWords = new HashSet<>();

            game(boards[n]);

            answer.append("\n");
        }
    }

    private static void game(char[][] board) {
        boolean[][] visited;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                visited = new boolean[4][4];
                visited[r][c] = true;

                makeString(board, r, c, visited, String.valueOf(board[r][c]));
            }
        }

        int score = 0;
        for (String word : foundWords) {
            if (word.length() <= 2) {
                // 점수 없음
            } else if (word.length() <= 4) {
                score += 1;
            } else if (word.length() == 5) {
                score += 2;
            } else if (word.length() == 6) {
                score += 3;
            } else if (word.length() == 7) {
                score += 5;
            } else {
                score += 11;
            }
        }
        answer.append(score)
                .append(" ")
                .append(maxStr)
                .append(" ")
                .append(count);
    }

    private static void makeString(char[][] board, int r, int c, boolean[][] visited, String str) {
        // 단어 길이가 8보다 길면 검사할 필요 없음
        if (str.length() > 8) return;

        // 현재까지의 문자열이 사전에 있는 단어인지 확인
        if (dictionary.containsKey(str) && !foundWords.contains(str)) {
            foundWords.add(str);
            count++;

            if (str.length() > maxLen) {
                maxLen = str.length();
                maxStr = str;
            } else if (str.length() == maxLen && str.compareTo(maxStr) < 0) {
                maxStr = str;
            }
        }

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (!isValid(nr, nc, visited)) continue;

            visited[nr][nc] = true;
            makeString(board, nr, nc, visited, str + board[nr][nc]);
            visited[nr][nc] = false;
        }
    }

    private static boolean isValid(int r, int c, boolean[][] visited) {
        return r >= 0 && r < 4 && c >= 0 && c < 4 && !visited[r][c];
    }

    private static void printResult() {
        System.out.println(answer);
    }
}