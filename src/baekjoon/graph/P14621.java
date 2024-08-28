package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 14621 (Kruskal Algorithm 활용)
 * 나만 안되는 연애
 */
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

        A = new int[N + 1];
        S = new String[N + 1];

        for (int i = 0; i < N; i++) {
            A[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < N + 1; i++) {
            S[i] = st.nextToken();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            pq.add(new Node(start, end, value));
        }

        int result = Kruskal(N);
        System.out.println(result);
    }

    static int Kruskal(int i) {
        int result = 0;
        int useEdge = 0;

        while (useEdge < i - 1) {
            if (pq.isEmpty()) return -1;
            Node now = pq.poll();
            if (Find(now.start) != Find(now.end) && !S[now.start].equals(S[now.end])) {
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
