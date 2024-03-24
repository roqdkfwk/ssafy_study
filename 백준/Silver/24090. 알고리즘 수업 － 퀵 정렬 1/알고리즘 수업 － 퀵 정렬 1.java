import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] A;
	static int K;
	static int count = 0;
	static int num1;
	static int num2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		quickSort(0, N - 1);
//		System.out.println(count);
		if (count < K) System.out.println(-1);
		else System.out.println(num1 + " " + num2);
	}

	static void quickSort(int left, int right) {

		if (left < right) {
			int pivot = partition(left, right);
			if (pivot == -1)
				return;

			// pivot은 자기 위치를 찾았으니 pivot기준 왼쪽과 오른쪽만 살피면 된다.
			quickSort(left, pivot - 1);
			quickSort(pivot + 1, right);
		}
	}

	static int partition(int left, int right) {

		int pivot = A[right];
		int i = left - 1;

		for (int j = left; j < right; j++) {

			if (A[j] <= pivot) {
				i++;	// i는 pivot보다 작은 값들이 모여있는 위치를 나타내는 경계
				int tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;
				count++;

				if (count == K) {
					num1 = A[i];
					num2 = A[j];
					return -1;
				}
			}
		}

		// pivot을 자기 위치로 보내야 함
		if (A[i + 1] != A[right]) {
			int tmp = A[i + 1];
			A[i + 1] = A[right];
			A[right] = tmp;
			count++;
			if (count == K) {
				num1 = A[i + 1];
				num2 = A[right];
				return -1;
			}
		}

		// pivot값의 위치를 반환해서 quickSort 메소드의 pivot값에 초기화
		// i다음의 위치가 pivot이 들어와야 하는 위치이다.
		// 따라서 (i + 1)값을 리턴함
		return i + 1; 
	}
}