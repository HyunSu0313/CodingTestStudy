package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1197 (최소 신장 트리(MST) 기초)
 * Prim Algorithm
 */
public class P1197_Prim {
    static ArrayList<Edge>[] A;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        A = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i <= V; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            A[start].add(new Edge(end, value));
            A[end].add(new Edge(start, value));
        }

        int result = Prim(1);

        System.out.println(result);
    }

    static int Prim(int start) {
        int result = 0;
        int edgesUsed = 0;

        pq.add(new Edge(start, 0));

        while (!pq.isEmpty() && edgesUsed < A.length - 1) {
            Edge now = pq.poll();

            if (visited[now.index]) continue;

            visited[now.index] = true;
            result += now.value;
            edgesUsed++;

            for (Edge e : A[now.index]) {
                if (!visited[e.index]) {
                    pq.add(e);
                }
            }
        }

        return result;
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
