package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P14621 {
    static int[] A;
    static String[] S;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    static void Kruskal(int i) {
        int result = 0;
        int useEdge = 0;

        while (useEdge < N - 1) {
            Node now = pq.poll();
            if (Find(now.start) != Find(now.end) && S[now.start] != S[now.end]) {
                Union(now.start, now.end);
            }
        }
    }

    static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    static int Find(int i) {
        if (A[i] == i) return i;
        else return A[i] = Find(A[i]);
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}
