package programmers.dfsandbfs;

import java.util.*;

/**
 * Programmers (BFS 활용)
 * 게임 맵 최단거리
 */
class P1844 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int N, M;

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N + 1][M + 1];
        A = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                A[i][j] = maps[i - 1][j - 1];
            }
        }

        BFS(1, 1);

        if (visited[N][M]) return A[N][M];
        else return -1;
    }

    static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && y > 0 && x <= N && y <= M) {
                    if (A[x][y] == 1 & !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
