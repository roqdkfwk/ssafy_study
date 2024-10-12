import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] numbers = new int[N];
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            set.add(numbers[i]);
        }
        
        Arrays.sort(numbers);
        
        for (int i = N - 1; i >= 0; i--) {
            int target = numbers[i];
            for (int j = 0; j <= i; j++) {
                for (int k = j; k <= i; k++) {
                    int sum = numbers[j] + numbers[k];
                    if (sum < target && set.contains(target - sum)) {
                        System.out.println(target);
                        return;
                    }
                }
            }
        }
    }
}