package baekjoon.tree;

import java.util.*;
import java.io.*;

public class P2263 {

    static int[] inorder;
    static int[] postorder;
    static int[] inorderIndex;
    static StringBuilder preorder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        inorder = new int[n];
        postorder = new int[n];
        inorderIndex = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderIndex[inorder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        buildPreorder(0, n - 1, 0, n - 1);

        System.out.println(preorder.toString());
    }

    public static void buildPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return;
        }

        int root = postorder[postEnd];
        preorder.append(root).append(" ");

        int rootIdx = inorderIndex[root];
        int leftTreeSize = rootIdx - inStart;

        buildPreorder(inStart, rootIdx - 1, postStart, postStart + leftTreeSize - 1);

        buildPreorder(rootIdx + 1, inEnd, postStart + leftTreeSize, postEnd - 1);
    }
}
