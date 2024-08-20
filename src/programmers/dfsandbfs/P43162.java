package programmers.dfsandbfs;

import java.util.*;

/**
 * Programmers (DFS 활용)
 * 네트워크
 */
class P43162 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        A = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    A[i].add(j);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                DFS(i);
            }
        }

        return count;
    }

    static void DFS(int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;

        for (int i : A[v]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }
}
