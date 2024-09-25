package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 21294 Kruskal Algorithm
 * 도시 건설
 */
public class P21924_Kruskal {
    static int[] A;
    static PriorityQueue<pNode> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = i;
        }

        long sum = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            sum += V;
            pq.add(new pNode(S, E, V));
        }

        long minValue = Kruskal(N);

        if (minValue == 0) System.out.println("-1");
        else System.out.println(sum - minValue);
    }

    private static long Kruskal(int N) {
        int useEdge = 0;
        long result = 0;

        while (!pq.isEmpty() && useEdge < N - 1) {
            pNode now = pq.poll();

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
