import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int R = str1.length();
        int C = str2.length();

        int maxLength = 0;
        int[][] dp = new int[R + 1][C + 1];
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (!(str1.charAt(r - 1) == str2.charAt(c - 1))) continue;

                dp[r][c] = dp[r - 1][c - 1] + 1;
                maxLength = Math.max(maxLength, dp[r][c]);
            }
        }

        System.out.println(maxLength);
    }
}