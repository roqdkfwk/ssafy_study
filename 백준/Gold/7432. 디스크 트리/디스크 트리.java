import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    static class Node {
        String Name;
        Map<String, Node> Folder ;

        public Node (String Name) {
            this.Name = Name;
            this.Folder = new TreeMap<>();
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Node root = new Node("");
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("\\\\");
            addFolder(root, str, 0);
        }

        printDirectory(root, 0);
    }

    static void addFolder(Node node, String[] str, int index) {
        if (index == str.length) {
            return;
        }

        if (!node.Folder.containsKey(str[index])) {
            node.Folder.put(str[index], new Node(str[index]));
        }

        addFolder(node.Folder.get(str[index]), str, index + 1);
    }

    static void printDirectory(Node node, int depth) {
        if (node.Name.length() > 0) {   // root 노드를 거르기 위해서
            for (int i = 1; i < depth; i++) {
                System.out.print(" ");
            }

            System.out.println(node.Name);
        }

        for (Node child : node.Folder.values()) {
            printDirectory(child, depth + 1);
        }
    }
}