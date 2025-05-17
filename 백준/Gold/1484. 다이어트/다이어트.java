import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());

        if (G < 3) {
            System.out.println(-1);
            return;
        }

        StringBuilder answer = new StringBuilder();
        int diff = 0;
        int now = 2;
        int before = 1;
        while (diff <= G) {
            int weight = (now * now) - (before * before);
            if (weight > G) {
                before++;
            } else if (weight < G) {
                now++;
                diff = (now * now) - ((now - 1) * (now - 1));
            } else {
                before++;
                answer.append(now).append("\n");
            }
        }
        if (answer.toString().length() == 0) System.out.println(-1);
        else System.out.println(answer.toString().trim());
    }
}