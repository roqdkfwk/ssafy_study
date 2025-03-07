import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] strArr = new String[N];
        for (int i = 0; i < N; i++) {
            strArr[i] = br.readLine();
        }

        int length = strArr[0].length();
        String answer = "";
        boolean flag = true;
        for (int i = 0; i < length; i++) {
            flag = true;
            for (int j = 0; j < N - 1; j++) {
                if (strArr[j].charAt(i) != strArr[j + 1].charAt(i)) {
                    flag = false;
                }
            }
            if (flag) {
                answer += strArr[0].charAt(i);
            } else {
                answer += "?";
            }
        }
    System.out.print(answer);
    }
}