package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 16398 (최소 신장 트리(MST) 기초)
 * Prim Algorithm
 */
public class P16398_Prim {
    static ArrayList<Edge>[] A;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        int[][] matrix = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (i != j) A[i].add(new Edge(j, matrix[i][j]));
            }
        }

        System.out.println(Prim(1));
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
