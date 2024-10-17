package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5639 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int[] preorder = new int[10000];
        int idx = 0;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            preorder[idx++] = Integer.parseInt(input);
        }

        Node root = buildBST(preorder, 0, idx - 1);

        postOrderTraversal(root);
    }

    private static Node buildBST(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }

        Node node = new Node(preorder[start]);

        int rightSubtreeIdx = start + 1;
        while (rightSubtreeIdx <= end && preorder[rightSubtreeIdx] < node.value) {
            rightSubtreeIdx++;
        }

        node.left = buildBST(preorder, start + 1, rightSubtreeIdx - 1);
        node.right = buildBST(preorder, rightSubtreeIdx, end);

        return node;
    }

    private static void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);

        System.out.println(node.value);
    }

    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }
}
