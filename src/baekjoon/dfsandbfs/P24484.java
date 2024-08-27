package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 백준 24484 (DFS 기본)
 */
public class P24484 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] d, t;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        d = new int[N + 1];
        t = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 1; i < N + 1; i++) {
            d[i] = -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(A[i]);
            Collections.reverse(A[i]);
        }

        DFS(R, 0);

        long result = 0;

        for (int i = 1; i < N + 1; i++) {
            result += (long) d[i] * t[i];
        }

        System.out.println(result);
    }

    static void DFS(int v, int depth) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;
        d[v] = depth;
        t[v] = count;

        for (int i : A[v]) {
            if (!visited[i]) {
                count++;
                DFS(i, depth + 1);
            }
        }
    }
}
