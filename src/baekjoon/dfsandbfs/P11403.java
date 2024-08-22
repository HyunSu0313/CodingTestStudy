package baekjoon.dfsandbfs;

import java.util.*;
import java.io.*;

/**
 * 백준 11403 (DFS & BFS 활용)
 * 경로 찾기
 */
public class P11403 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[][] B, result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N];
        B = new int[N][N];
        result = new int[N][N];

        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (B[i][j] == 1) {
                    A[i].add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
//            DFS(i, 1);
            BFS(i);
            for (int j = 0; j < N; j++) {
                if (visited[j]) result[i][j] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void DFS(int v, int depth) {
        if (visited[v]) {
            return;
        }

        if (depth != 1) {
            visited[v] = true;
        }

        for (int i : A[v]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }
    }

    static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int i : A[x]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
