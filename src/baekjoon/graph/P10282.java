package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 10282 Dijkstra
 * 해킹
 */
public class P10282 {
    static ArrayList<Edge>[] A;
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            A = new ArrayList[N + 1];
            for (int j = 1; j < N + 1; j++) {
                A[j] = new ArrayList<>();
            }

            distance = new int[N + 1];
            for (int j = 0; j < N + 1; j++) {
                distance[j] = Integer.MAX_VALUE;
            }
            visited = new boolean[N + 1];

            for (int j = 0; j < D; j++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());

                A[E].add(new Edge(S, V));
            }

            Dijkstra(C);

            int count = 0;
            int max = 0;

            for (int j = 1; j < N + 1; j++) {
                if (distance[j] != Integer.MAX_VALUE) {
                    count++;
                    if (max < distance[j]) max = distance[j];
                }
            }

            System.out.println(count + " " + max);
        }

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
