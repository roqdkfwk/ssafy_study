import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	
	static class Node {
		
		Character data;
		Node left;
		Node right;
		
		Node() {}
		Node(char data) {
			this.data = data;
		}
	}
	
	// VLR
	static void preorder(Node node) { 
		
		if (node == null) return;
		
		System.out.print(node.data);
		preorder(node.left);
		preorder(node.right);
	}
	
	// LVR
	static void inorder(Node node) {
		
		if (node == null) return;
		
		inorder(node.left);
		System.out.print(node.data);
		inorder(node.right);
	}
	
	// LRV
	static void postorder(Node node) {
		
		if (node == null) return;
		
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N + 1];
		for (int i = 1; i < N + 1; i++) nodes[i] = new Node();
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			char parentData = st.nextToken().charAt(0);
			char leftChild = st.nextToken().charAt(0);
			char rightChild = st.nextToken().charAt(0);
			
			nodes[parentData - 'A' + 1].data = parentData;
			
			// 부모 노드에 대응되는 자식 노드 연결
			if (leftChild != '.') {
				nodes[parentData - 'A' + 1].left = nodes[leftChild - 'A' + 1];
				nodes[leftChild - 'A' + 1].data = leftChild;
			}
			if (rightChild != '.') {
				nodes[parentData - 'A' + 1].right = nodes[rightChild - 'A' + 1];
				nodes[rightChild - 'A' + 1].data = rightChild;
			}
		}

		preorder(nodes[1]);
		System.out.println();
		inorder(nodes[1]);
		System.out.println();
		postorder(nodes[1]);
	}
}