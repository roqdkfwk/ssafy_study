import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deque = new LinkedList<Integer>();
		
		int N = Integer.parseInt(br.readLine());
		int[] numArr = new int[N];
		for (int i = 0; i < N; i++) numArr[i] = i + 1;
		
		deque.offerFirst(N);
		if (N == 1) System.out.println(1);
		else {
			while (N > 1) {
//				deque의 앞에 이전에 넣은 숫자보다 1만큼 작은 값을 넣고
				deque.offerFirst(--N);
//				넣은 숫자의 크기만큼 반복해서 뒤의 카드를 앞으로 옮기는 행동을 반복
				for (int i = 0; i < N; i++) deque.offerFirst(deque.pollLast());
//				System.out.println(deque.peekFirst());
//				System.out.println(deque.peekLast());
//				System.out.println();
			}
			
//			Iterator는 무엇??
			Iterator iter = deque.iterator();
			while(iter.hasNext()) System.out.print(iter.next() + " ");
		}
	}
}