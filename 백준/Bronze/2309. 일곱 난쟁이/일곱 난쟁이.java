import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Integer> nan = new ArrayList<>();
		
		for (int i = 0; i < 9; i++) {
			nan.add(sc.nextInt());
		}
		
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += nan.get(i);
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - nan.get(i) - nan.get(j) == 100) {
					nan.remove(j);
					nan.remove(i);
					break;
				}
			}
			if (nan.size() == 7) {
				break;
			}
		}
		
		int[] height = new int[7];
		for (int i = 0; i < 7; i++) {
			height[i] = nan.get(i);
		}
		Arrays.sort(height);
		for (int i = 0; i < 7; i++) {
			System.out.println(height[i]);
		}
	}
}