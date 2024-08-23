package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 24479 (BFS 기본)
 */
public class P24445 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] order;
    static int d = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        order = new int[N + 1];

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

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(A[i]);
            Collections.reverse(A[i]);
        }

        BFS(R);

        for (int i = 1; i < order.length; i++) {
            System.out.println(order[i]);
        }
    }

    static void BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        visited[i] = true;
        order[i] = d;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int v : A[x]) {
                if (!visited[v]) {
                    d++;
                    visited[v] = true;
                    order[v] = d;
                    queue.add(v);
                }
            }
        }
    }
}
