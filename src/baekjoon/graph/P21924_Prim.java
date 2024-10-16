package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 21294 Prim Algorithm
 * 도시 건설
 */
public class P21924_Prim {
    static ArrayList<Edge>[] A;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        long sum = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            sum += V;
            A[S].add(new Edge(E, V));
            A[E].add(new Edge(S, V));
        }

        long result = Prim(1);

        if (result == 0) System.out.println("-1");
        else System.out.println(sum - result);
    }

    private static long Prim(int start) {
        pq.add(new Edge(start, 0));
        int useEdge = 0;
        long result = 0;

        while (!pq.isEmpty() && useEdge < A.length - 1) {
            Edge now = pq.poll();

            if (visited[now.index]) continue;

            visited[now.index] = true;
            result += now.value;
            useEdge++;

            for (Edge e : A[now.index]) {
                if (!visited[e.index]) {
                    pq.add(e);
                }
            }
        }

        if (useEdge == A.length - 1) return result;
        else return 0;
    }

    static class Edge implements Comparable<Edge> {
        int index;
        int value;

        public Edge(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
}
