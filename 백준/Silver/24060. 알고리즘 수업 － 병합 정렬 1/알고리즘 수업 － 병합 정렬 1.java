import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] A;
	static int[] sortedA;
	static int K;
	static int count = 0;
	static int number = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		sortedA = new int[N];
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		mergeSort(0, N - 1);
		System.out.println(number);
	}
	
	static void mergeSort(int left, int right) {
		
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}	// mergeSort 메소드

	static void merge(int left, int mid, int right) {
		
		int L = left;
		int R = mid + 1;
		int idx = left;
		
		while (L <= mid && R <= right) {
			
			if (A[L] <= A[R]) {
				sortedA[idx] = A[L];
				idx++;
				L++;
			} else {
				sortedA[idx] = A[R];
				idx++;
				R++;
			}
		}
		
		if (L <= mid) {
			for (int i = L; i <= mid; i++) {
				
				sortedA[idx] = A[i];
				idx++;
			}
		} else {
			for (int i = R; i <= right; i++) {
				
				sortedA[idx] = A[i];
				idx++;
			}
		}
		
		for (int i = left; i <= right; i++) {
			
			A[i] = sortedA[i];
			count++;
			if (count == K) {
				number = A[i];
				return;
			}
		}
	}	// merge 메소드
}