package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2644 (BFS 활용)
 * 촌수 계산
 */
public class P2644 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int N, h1, h2;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        h1 = Integer.parseInt(st.nextToken());
        h2 = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        BFS(h1);

        for (int i = 1; i < N + 1; i++) {
            if (distance[i] == 0) distance[i] = -1;
        }

        System.out.println(distance[h2]);
    }

    static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i : A[now]) {
                if (!visited[i]) {
                    visited[i] = true;
                    distance[i] = distance[now] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
