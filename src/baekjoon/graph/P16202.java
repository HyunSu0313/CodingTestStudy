package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 16202 Kruskal Algorithm
 * MST 게임
 */
public class P16202 {
    static int[] A;
    static PriorityQueue<pNode>[] pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue[K];
        for (int i = 0; i < K; i++) {
            pq[i] = new PriorityQueue<>();
        }

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int j = 0; j < K; j++) {
                pq[j].add(new pNode(S, E, i));
            }
        }

        long[] result = new long[K];
        for (int i = 0; i < K; i++) {
            if (i > 0 && result[i - 1] == 0) break;
            result[i] = Kruskal(pq[i], i, N);
        }

        for (int i = 0; i < K; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static long Kruskal(PriorityQueue<pNode> priorityQueue, int t, int N) {
        for (int i = 0; i < t; i++) {
            priorityQueue.poll();
        }

        A = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = i;
        }

        int useEdge = 0;
        long result = 0;

        while (!priorityQueue.isEmpty() && useEdge < N - 1) {
            pNode now = priorityQueue.poll();

            if (Find(now.start) != Find(now.end)) {
                Union(now.start, now.end);
                result += now.value;
                useEdge++;
            }
        }

        if (useEdge == N - 1) return result;
        else return 0;
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
