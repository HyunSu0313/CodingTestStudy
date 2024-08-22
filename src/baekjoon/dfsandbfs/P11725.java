package baekjoon.dfsandbfs;

import java.util.*;
import java.io.*;

/**
 * 백준 11725 (BFS 활용)
 * 트리의 부모 찾기
 */
public class P11725 {
    static boolean[] visited;
    static ArrayList<Integer>[] A;
    static int N;
    static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        B = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < A.length; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            A[b].add(a);
        }

        BFS(1);

        for (int i = 2; i < B.length; i++) {
            System.out.println(B[i]);
        }
    }

    static void BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            int n = queue.poll();

            for (int x : A[n]) {
                if (!visited[x]) {
                    visited[x] = true;
                    B[x] = n;
                    queue.add(x);
                }
            }
        }
    }
}
