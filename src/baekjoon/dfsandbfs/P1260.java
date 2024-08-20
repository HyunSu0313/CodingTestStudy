package baekjoon.dfsandbfs;

import java.util.*;
import java.io.*;

/**
 * 백준 1260 (DFS와 BFS 기본)
 */
public class P1260 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        A = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        // 번호가 작은 것을 먼저 방문하기 위한 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(A[i]);
        }

        DFS(start);
        System.out.println();
        Arrays.fill(visited, false);
        BFS(start);
    }


    private static void DFS(int v) {
        if (visited[v]) {
            return;
        }

        System.out.print(v + " ");
        visited[v] = true;

        for (int i : A[v]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    private static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int i = queue.poll();
            System.out.print(i + " ");
            for (int n : A[i]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
