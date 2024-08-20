package baekjoon.dfsandbfs;

import java.io.*;
import java.util.*;

/**
 * 백준 2023 (DFS 활용)
 * 바이러스
 */
public class P2606 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        DFS(1);

        int count = 0;

        for (int i = 2; i < N + 1; i++) {
            if (visited[i]) count++;
        }

        System.out.println(count);

    }

    static void DFS(int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;

        for(int i : A[v]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }
}
