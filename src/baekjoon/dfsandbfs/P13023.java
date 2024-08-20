package baekjoon.dfsandbfs;

import java.io.*;
import java.util.*;

/**
 * 백준 13023 (DFS 활용)
 * 친구 관계 파악하기
 */
public class P13023 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static boolean arrive;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        A = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        for (int i = 0; i < n; i++) {
            DFS(i, 1);

            if (arrive) break;
        }

        if (arrive) System.out.println("1");
        else System.out.println("0");
    }

    static void DFS(int now, int depth) {
        if (arrive || depth == 5) {
            arrive = true;
            return;
        }

        visited[now] = true;

        for (int i : A[now]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }

        visited[now] = false;
    }
}
