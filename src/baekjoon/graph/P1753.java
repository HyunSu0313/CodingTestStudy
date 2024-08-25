package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1753 (다익스트라 알고리즘 기본)
 */
public class P1753 {
    static int V,E,K;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Edge>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        distance = new int[V + 1];
        visited = new boolean[V + 1];
        A = new ArrayList[V + 1];

        for (int i = 1; i < V + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < V + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            A[start].add(new Edge(end, value));
        }

        Dijkstra(K);

        for (int i = 1; i < V + 1; i++) {
            if (visited[i]) System.out.println(distance[i]);
            else System.out.println("INF");
        }
    }

    static void Dijkstra(int k) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(k, 0));
        distance[k] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowIndex = now.index;
            if (visited[nowIndex]) continue;
            visited[nowIndex] = true;

            for (int i = 0; i < A[nowIndex].size(); i++) {
                Edge tmp = A[nowIndex].get(i);
                int next = tmp.index;
                int value = tmp.value;
                if (distance[next] > distance[nowIndex] + value) {
                    distance[next] = value + distance[nowIndex];
                    pq.add(new Edge(next, distance[next]));
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
