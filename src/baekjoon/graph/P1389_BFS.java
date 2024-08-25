package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1389 (Floyd-Warshall 문제)
 * BFS 활용한 풀이
 */
public class P1389_BFS {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] distance;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];

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

        int min = 999999999;
        int minIndex = 1;
        int[] result = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            result[i] = BFS(i);
            if (min > result[i]){
                min = result[i];
                minIndex = i;
            }
        }

        System.out.println(minIndex);
    }

    static int BFS(int i) {
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;

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

        int count = 0;
        for (int k = 1; k < N + 1; k++) {
            count += distance[k];
        }

        return count;
    }
}
