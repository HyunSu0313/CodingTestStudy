package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 백준 1707 (그래프 활용)
 * 이분 그래프 판별
 */
public class P1707 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] check;
    static boolean isEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            A = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            check = new int[V + 1];
            isEven = true;

            for (int j = 1; j < V + 1; j++) {
                A[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                A[u].add(v);
                A[v].add(u);
            }

            for (int j = 1; j < V + 1; j++) {
                if (isEven) {
                    DFS(j);
                } else break;
            }

            if (isEven) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static void DFS(int v) {
        visited[v] = true;

        for (int i : A[v]) {
            if (!visited[i]) {
                check[i] = (check[v] + 1) % 2;
                DFS(i);
            } else if (check[v] == check[i]) {
                isEven = false;
            }
        }

    }
}
