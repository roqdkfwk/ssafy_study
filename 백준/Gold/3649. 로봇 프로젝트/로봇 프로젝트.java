import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine()) != null) {
            int hole = Integer.parseInt(input) * 10_000_000;

            int N = Integer.parseInt(br.readLine());
            int[] legos = new int[N];
            for (int i = 0; i < N; i++) {
                legos[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(legos);

            if (N < 2) {
                System.out.println("danger");
                continue;
            }

            int idx1 = 0;
            int idx2 = N - 1;
            int sum = 0;
            int maxDiff = -1;
            int bestL1 = 0, bestL2 = 0;
            boolean flag = false;
            while (idx1 < idx2) {
                sum = legos[idx1] + legos[idx2];

                if (sum < hole) idx1++;
                else if (sum > hole) idx2--;
                else {
                    int diff = Math.abs(legos[idx2] - legos[idx1]);
                    if (diff > maxDiff) {
                        maxDiff = diff;
                        bestL1 = legos[idx1];
                        bestL2 = legos[idx2];
                    }

                    flag = true;
                    idx1++;
                    idx2--;
                }
            }

            if (flag) System.out.println("yes " + bestL1 + " " + bestL2);
            else System.out.println("danger");
        }
    }
}