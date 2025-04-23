import java.io.*;
public class Main {

    static int T;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] sentence = br.readLine().split(" ");
            StringBuilder sb;
            for (int i = 0; i < sentence.length; i++) {
                sb = new StringBuilder(sentence[i]);
                answer.append(sb.reverse()).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer.toString().trim());
    }
}