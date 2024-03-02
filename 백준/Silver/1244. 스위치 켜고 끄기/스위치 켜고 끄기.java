import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	// 스위치의 개수
	static int[] tmdnlcl;	// 스위치의 상태를 나타낼 배열
	static int stu;	// 학생 수
	static int gen;	// 성별
	static int num;	// 스위치 번호
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tmdnlcl = new int[N];
		
		st = new StringTokenizer(br.readLine());	// 스위치의 현재 상태
		for (int i = 0; i < N; i++) tmdnlcl[i] = Integer.parseInt(st.nextToken());
		
		stu = Integer.parseInt(br.readLine());
		for (int n = 0; n < stu; n++) {
			
			st = new StringTokenizer(br.readLine());
			gen = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			
//			System.out.println(Arrays.toString(tmdnlcl));
			
			// 성별이 남자이면
			if (gen == 1) {
				// 스위치 번호는 1부터 시작
				for (int i = num - 1; i < N; i += num) tmdnlcl[i] = (tmdnlcl[i] + 1) % 2;	// 원래 숫자가 0이었으면 1, 1이었으면 0으로 바꿈
			}
			// 성별이 여자이면
			else {
				tmdnlcl[num - 1] = (tmdnlcl[num - 1] + 1) % 2;
				int idx = 1;
				// 배열의 인덱스 범위를 벗어나지 않고 && 양 옆으로 두 숫자가 같으면 스위치를 바꿈
				while (num - 1 - idx >= 0 && num - 1 + idx < N && tmdnlcl[num - 1 - idx] == tmdnlcl[num - 1 + idx]) {
					tmdnlcl[num - 1 - idx] = (tmdnlcl[num - 1 - idx] + 1) % 2;
					tmdnlcl[num - 1 + idx] = (tmdnlcl[num - 1 + idx] + 1) % 2;
					idx++;
				}
			}
			
//			System.out.println(Arrays.toString(tmdnlcl));
		}	// n에 대한 for문
		
		for (int i = 1; i < N + 1; i++) {
			sb.append(tmdnlcl[i - 1] + " ");
			if (i % 20 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}