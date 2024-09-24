package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1446 Dijkstra
 * 고속도로 지름길
 */
public class P1446 {
    static ArrayList<Edge>[] A;
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        A = new ArrayList[10001];
        for (int i = 0; i < 10001; i++) {
            A[i] = new ArrayList<>();
        }

        distance = new int[10001];
        for (int i = 0; i < 10001; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        visited = new boolean[10001];

        for (int i = 0; i < 10000; i++) {
            A[i].add(new Edge(i + 1, 1));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            A[S].add(new Edge(V, E));
        }

        Dijkstra(0);

        System.out.println(distance[D]);
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
