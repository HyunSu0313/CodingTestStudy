package programmers.dfsandbfs;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class P49189 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] distance;

    public int solution(int n, int[][] edge) {
        A = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        distance = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int u = edge[i][0];
            int v = edge[i][1];

            A[u].add(v);
            A[v].add(u);
        }

        BFS(1);

        int max = 0;

        for (int i = 2; i < n + 1; i++) {
            if (max < distance[i]) max = distance[i];
        }

        int count = 0;

        for (int i = 2; i < n + 1; i++) {
            if (max == distance[i]) count++;
        }

        return count;
    }

    static void BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for (int x : A[now]) {
                if (!visited[x]) {
                    visited[x] = true;
                    distance[x] = distance[now] + 1;
                    queue.add(x);
                }
            }
        }
    }
}
