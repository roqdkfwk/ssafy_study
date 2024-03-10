import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		
		int number;
		List<Node> nodeQ = new ArrayList<>();
		
		Node() {}
		
		Node(int number) {
			this.number = number;
		}
	}	// Node 클래스
	
	static int N;	// 컴퓨터의 개수
	static int M;	//
	static Node[] nodes;
	static boolean[] visit;
	static int[] maxVisit;
	static int count;	// 한 번에 해킹할 수 있는 컴퓨터의 개수
	static int maxCount = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nodes = new Node[N + 1];
		maxVisit = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			nodes[i] = new Node();
			nodes[i].number = i;
		}
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int child = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			nodes[parent].nodeQ.add(nodes[child]);	// 컴퓨터의 신뢰 관계			
		}
		
		for (int i = 1; i < N + 1; i++) {
			visit = new boolean[N + 1];	// 시작 위치가 달라질 때마다 방문했던 컴퓨터를 새로 초기화
			count = 0;	// 한 번에 해킹할 수 있는 컴퓨터의 수 초기화
			search(i);
			
			maxVisit[i] = count;
			if (maxCount < count) maxCount = count;
		}
		
//		for (int i = 1; i < N + 1; i++) {
//			while (!nodes[i].nodeQ.isEmpty()) {
//				System.out.println(i + " : " + nodes[i].nodeQ.poll().number);
//			}
//		}
		
		for (int i = 1; i < N + 1; i++) {
			
			if (maxVisit[i] == maxCount) sb.append(i + " ");
		}
		
//		System.out.println(Arrays.toString(maxVisit));
		
		System.out.println(sb);
	}
	
	static void search(int nodeNumber) {
		
//		System.out.println("count : " + count + ", nodeNumber : " + nodeNumber + ", ");
		
		if (nodes[nodeNumber].nodeQ.isEmpty() && !visit[nodeNumber]) {	// 리프노드라서 자식노드 Queue가 비어있지만 처음 방문하는 노드인 경우
			visit[nodeNumber] = true;	// 방문처리 후
			count++; // count를 1만큼 증가시킨다.
			return;
		}
		
		visit[nodeNumber] = true;
		count++;
		
//		System.out.println("count : " + count + ", nodeNumber : " + nodeNumber + ", ");
		
		for (int i = 0; i < nodes[nodeNumber].nodeQ.size(); i++) {
			
			if (!visit[nodes[nodeNumber].nodeQ.get(i).number]) {
//				count++;
				visit[nodes[nodeNumber].nodeQ.get(i).number] = true;
				search(nodes[nodeNumber].nodeQ.get(i).number);
			}
		}
		
//		while (!nodes[nodeNumber].nodeQ.isEmpty()) {	// 자식 노드가 남아있다면 계속 반복 
//			
//			if (!visit[nodes[nodeNumber].nodeQ.peek().number]) {	// 자식노드 Queue에 담겨 있는 노드 중 가장 위의 노드가 방문하지 않았던 노드라면
//				count++;	// count를 1만큼 증가시키고
//				search(nodes[nodeNumber].nodeQ.poll().number);	// 노드의 Queue에 담겨있는 가장 위의 노드의 번호로 다시 탐색 후 노드를 버림
//			} else {
//				nodes[nodeNumber].nodeQ.poll();	// 방문했던 노드라면 버림
//			}			
//		}
//		Queue를 쓰면 poll때문에 잘 안되네
		return;
	}	// search 메소드
}