package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 3584 LCA(최소 공통 조상) 구하기 (성능 최적화 O)
 */
public class P3584 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] depth;
    static int[][] parent;
    static int kmax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            tree = new ArrayList[N + 1];
            for (int j = 1; j < N + 1; j++) {
                tree[j] = new ArrayList<>();
            }

            visited = new boolean[N + 1];
            depth = new int[N + 1];

            int temp = 1;
            kmax = 0;
            while (temp <= N) {
                temp <<= 1;
                kmax++;
            }

            parent = new int[kmax + 1][N + 1];

            for (int j = 0; j < N - 1; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                parent[0][e] = s;

                tree[s].add(e);
                tree[e].add(s);
            }

            int root = 0;
            for (int j = 1; j < N; j++) {
                if (parent[0][j] == 0) {
                    root = j;
                    break;
                }
            }

            DFS(root);
            for (int k = 1; k <= kmax; k++) {
                for (int n = 1; n <= N; n++) {
                    parent[k][n] = parent[k - 1][parent[k - 1][n]];
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(excuteLCA(a, b));
        }
    }

    private static int excuteLCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int k = kmax; k >= 0; k--) {
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }

        for (int k = kmax; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int LCA = a;
        if (a != b) LCA = parent[0][LCA];
        return LCA;
    }

    private static void DFS(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int next : tree[node]) {
            if (!visited[next]) {
                depth[next] = depth[node] + 1;
                DFS(next);
            }
        }

    }
}
