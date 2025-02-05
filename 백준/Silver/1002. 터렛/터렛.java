import java.util.*;
import java.io.*;
public class Main {

    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        InputHandler();

        printResult();
    }

    private static void printResult() {
        System.out.println(sb.toString().trim());
    }

    private static void findLocation(double r1, double c1, double d1, double r2, double c2, double d2) {
        if (r1 == r2 && c1 == c2 && d1 == d2) {
            sb.append(-1).append("\n");
            return;
        }

        double distance = length(r1, c1, r2, c2);
        double largeRadius = Math.max(d1, d2);
        double smallRadius = Math.min(d1, d2);
        if (distance == d1 + d2 || distance == largeRadius - smallRadius) {
            sb.append(1).append("\n");
        } else if (distance > d1 + d2 || largeRadius - smallRadius > distance) {
            sb.append(0).append("\n");
        } else {
            sb.append(2).append("\n");
        }
    }

    private static double length(double r1, double c1, double r2, double c2) {
        return Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(c1 - c2, 2));
    }

    private static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            double r1 = Integer.parseInt(st.nextToken());
            double c1 = Integer.parseInt(st.nextToken());
            double d1 = Integer.parseInt(st.nextToken());
            double r2 = Integer.parseInt(st.nextToken());
            double c2 = Integer.parseInt(st.nextToken());
            double d2 = Integer.parseInt(st.nextToken());
            findLocation(r1, c1, d1, r2, c2, d2);
        }
    }
}