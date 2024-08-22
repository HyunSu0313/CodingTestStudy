package baekjoon.dfsandbfs;

import java.io.*;
import java.util.*;

/**
 * 백준 P1967 (BFS 활용)
 * 트리의 지름
 */
public class P1967 {
    static boolean[] visited;
    static int[] distance;
    static ArrayList<Edge>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        A = new ArrayList[n + 1];
        distance = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            A[a].add(new Edge(b, c));
            A[b].add(new Edge(a, c));
        }

        BFS(1);
        int max_index = 1;
        for (int i = 2; i < distance.length; i++) {
            if (distance[max_index] < distance[i]) max_index = i;
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];

        BFS(max_index);
        int max = 0;
        for (int i = 1; i < distance.length; i++) {
            if (max < distance[i]) max = distance[i];
        }

        System.out.println(max);
    }

    static void BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int current = distance[x];
            for (Edge e : A[x]) {
                int index = e.index;
                int value = e.value;

                if (!visited[index]) {
                    visited[index] = true;
                    queue.add(index);
                    distance[index] = current + value;
                }
            }
        }
    }

    static class Edge {
        int index;
        int value;

        public Edge(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
