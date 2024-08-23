import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int A;
	static int[] seq;
	static int[] list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		A = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		seq = new int[A];
		list = new int[A];
		for (int i = 0; i < A; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		list[0] = seq[0];
		int len = 1;
		
		for (int i = 1; i < A; i++) {
			int key = seq[i];
			
			if (list[len - 1] < key) {
				list[len] = key;
				len++;
			} else {
				int left = 0;
				int right = len;
				while (left < right) {
					int mid = (left + right) / 2;
					if (list[mid] < key) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}	// while
				list[left] = key;
			}
		}
		
		System.out.println(len);
	}	// main
}