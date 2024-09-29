package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10868 {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int length = n;
        int treeDepth = 0;

        while (length != 0) {
            length /= 2;
            treeDepth++;
        }

        int arrayLength = (int) Math.pow(2, treeDepth) * 2;
        int leftNodeStart = arrayLength - 1;

        tree = new long[arrayLength + 1];

        
    }
}
