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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            A[start].add(new Edge(end, value));
            A[end].add(new Edge(start, value));
        }

        int result = Prim(1); // 시작 정점은 1로 설정

        System.out.println(result);
    }

    static int Prim(int start) {
        int result = 0;
        int useEdge = 0;

        pq.add(new Edge(start, 0));

        while (!pq.isEmpty() && useEdge < A.length - 2) {
            Edge now = pq.poll();

            if (!visited[now.index]) {
                visited[now.index] = true;
                result += now.value;
                useEdge++;

                for (Edge edge : A[now.index]) {
                    if (!visited[edge.index]) {
                        pq.add(edge);
                    }
                }
            }
        }

        return result;
    }

    static class Edge implements Comparable<Edge> {
        int index;
        int value;

        public Edge(int target, int weight) {
            this.index = target;
            this.value = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
}
