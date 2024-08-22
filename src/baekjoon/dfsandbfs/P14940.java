package baekjoon.dfsandbfs;

import java.util.*;
import java.io.*;

/**
 * 백준 14940 (BFS 활용)
 * 쉬운 최단 거리
 */
public class P14940 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] A;
    static int[][] distance;
    static int N, M, START_X, START_Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        distance = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] == 2) {
                    START_X = i;
                    START_Y = j;
                }
            }
        }

        BFS(START_X, START_Y);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && A[i][j] != 0) {
                    distance[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int n = distance[now[0]][now[1]];
            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x >= 0 && y >= 0 && x < N && y < M) {
                    if (A[x][y] == 1 && !visited[x][y]) {
                        visited[x][y] = true;
                        distance[x][y] = n + 1;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }
}
