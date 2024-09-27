package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 11725 트리 기초
 */
public class P11725 {
    static ArrayList<Integer>[] A;
    static int[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        result = new int[N + 1];

        for (int i = 0; i  < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            A[S].add(E);
            A[E].add(S);
        }

        DFS(1);

        for (int i = 2; i < N + 1; i++) {
            System.out.println(result[i]);
        }
    }

    static void DFS(int i) {
        if (visited[i]) {
            return;
        }

        visited[i] = true;

        for (int v : A[i]) {
            if (!visited[v]) {
                result[v] = i;
                DFS(v);
            }
        }
    }
}
