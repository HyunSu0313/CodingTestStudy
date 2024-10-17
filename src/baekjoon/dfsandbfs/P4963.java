package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4963 {
    static int[] dx = {0, 1, 0, -1, -1, -1, 1, 1};
    static int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[][] A;
    static boolean[][] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) return;

            A = new int[M + 1][N + 1];
            visited = new boolean[M + 1][N + 1];

            for (int i = 1; i < M + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < N + 1; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 1; i < M + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (!visited[i][j] && A[i][j] == 1) {
                        BFS(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.add(new int[] {i, j});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 8; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && x < M + 1 && y > 0 && y < N + 1) {
                    if (!visited[x][y] && A[x][y] == 1) {
                        visited[x][y] = true;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
