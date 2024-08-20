package baekjoon.dfsandbfs;

import java.util.*;

/**
 * 백준 1167 (BFS 활용)
 * 트리의 지름
 */
public class P1167 {

    static boolean[] visited;
    static int[] distance;
    static ArrayList<Edge>[] A;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        A = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int S = scanner.nextInt();
            while (true) {
                int E = scanner.nextInt();
                if (E == -1) break;
                int V = scanner.nextInt();
                A[S].add(new Edge(E, V));
            }
        }

        distance = new int[N + 1];
        visited = new boolean[N + 1];

        BFS(1);
        int Max = 1;

        for (int i = 2; i <= N; i++) {
            if (distance[Max] < distance[i]) Max = i;
        }

        distance = new int[N + 1];
        visited = new boolean[N + 1];
        BFS(Max);
        Arrays.sort(distance);
        System.out.println(distance[N]);
    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int now_node = queue.poll();
            visited[now_node] = true;

            for (Edge i : A[now_node]) {
                int e = i.e;
                int v = i.value;
                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    distance[e] = distance[now_node] + v;
                }
            }
        }
    }

    static class Edge {
        int e;
        int value;

        public Edge(int e, int value) {
            this.e = e;
            this.value = value;
        }
    }
}

