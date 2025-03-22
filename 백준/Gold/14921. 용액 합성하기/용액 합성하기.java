import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[] arr;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            treeSet.add(sum);

            if (Math.abs(answer) > Math.abs(sum)) answer = sum;

            if (sum < 0) left++;
            else right--;
        }

        System.out.println(answer);
    }
}