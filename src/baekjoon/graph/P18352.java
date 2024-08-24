package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 18352 (그래프 탐색 기본)
 */
public class P18352 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
        }

        BFS(X);

        if (isChecked(distance, K)) {
            for (int i = 1; i < N + 1; i++) {
                if (distance[i] == K) System.out.println(i);
            }
        } else System.out.println("-1");
    }

    static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int x : A[now]) {
                if (!visited[x]) {
                    visited[x] = true;
                    distance[x] = distance[now] + 1;
                    queue.add(x);
                }
            }
        }
    }

    static boolean isChecked(int[] array, int n) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] == n) return true;
        }
        return false;
    }
}
