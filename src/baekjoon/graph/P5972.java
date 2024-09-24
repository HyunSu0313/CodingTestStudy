package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 5972 Dijkstra
 * 택배 배송
 */
public class P5972 {
    static ArrayList<Edge>[] A;
    static int[] distance;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }
        distance = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            A[S].add(new Edge(E, V));
            A[E].add(new Edge(S, V));
        }

        Dijkstra(1);

        System.out.println(distance[N]);
    }

    private static void Dijkstra(int i) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(i, 0));
        distance[i] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowIndex = now.index;

            if (!visited[nowIndex]) {
                visited[nowIndex] = true;

                for (Edge e : A[nowIndex]) {
                    if (!visited[e.index] && distance[e.index] > distance[nowIndex] + e.value) {
                        distance[e.index] = distance[nowIndex] + e.value;
                        pq.offer(new Edge(e.index, distance[e.index]));
                    }
                }
            }
        }

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
