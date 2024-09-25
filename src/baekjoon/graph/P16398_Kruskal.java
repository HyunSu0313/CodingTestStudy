package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 16398 (최소 신장 트리(MST) 기초)
 * Kruskal Algorithm
 */
public class P16398_Kruskal {
    static int[] A;
    static PriorityQueue<pNode> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = i;
        }

        int[][] matrix = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (i != j) pq.add(new pNode(i, j, matrix[i][j]));
            }
        }

        System.out.println(Kruskal(N));
    }

    private static long Kruskal(int N) {
        int useEdge = 0;
        long result = 0;

        while (useEdge < N - 1) {
            pNode now = pq.poll();

            if (Find(now.start) != Find(now.end)) {
                Union(now.start, now.end);
                result += now.value;
                useEdge++;
            }
        }

        return result;
    }

    private static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    private static int Find(int i) {
        if (A[i] == i) return i;
        else return A[i] = Find(A[i]);
    }

    static class pNode implements Comparable<pNode> {
        int start;
        int end;
        int value;

        public pNode(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(pNode o) {
            return this.value - o.value;
        }
    }
}
