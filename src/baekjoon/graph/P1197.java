package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1197 (최소 신장 트리 기초)
 * Kruskal Algorithm
 */
public class P1197 {
    static int[] A;
    static PriorityQueue<pNode> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        A = new int[N + 1];

        for (int i = 0; i < N; i++) {
            A[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            pq.add(new pNode(start, end, value));
        }

        int result = Kruskal(N);

        System.out.println(result);
    }

    private static int Kruskal(int N) {
        int useEdge = 0;
        int result = 0;

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

    static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    static int Find (int i) {
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
