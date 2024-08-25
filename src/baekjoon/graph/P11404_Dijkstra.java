package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 11404 (Floyd-Warshall 기본)
 * Dijkstra 이용한 풀이
 */
public class P11404_Dijkstra {
    static ArrayList<Edge>[] A;
    static boolean[] visited;
    static int[] distance;
    static int[][] D;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        D = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            A[start].add(new Edge(end, value));
        }

        for (int i = 1; i < N + 1; i++) {
            Dijkstra(i);
            for (int j = 1; j < N + 1; j++) {
                if (distance[j] != Integer.MAX_VALUE) {
                    D[i][j] = distance[j];
                } else {
                    D[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void Dijkstra(int i) {
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        for (int k = 0; k < N + 1; k++) {
            distance[k] = Integer.MAX_VALUE;
        }

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
                        pq.add(new Edge(e.index, distance[e.index]));
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
