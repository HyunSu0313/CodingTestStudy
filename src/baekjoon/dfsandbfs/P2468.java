package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2468 (BFS 활용)
 * 안전 구역 찾기
 */
public class P2468 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        int[][] B = new int[N][N];

        int min = 100;
        int max = 1;
        int max_count = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n < min) min = n;
                if (n > max) max = n;
                A[i][j] = n;
                B[i][j] = n;
            }
        }

        for (int i = min; i <= max; i++) {
            visited = new boolean[N][N];
            int count = 0;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (B[j][k] <= i) A[j][k] = 0;
                    else A[j][k] = 1;
                }
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (A[j][k] != 0 && !visited[j][k]) {
                        count++;
                        BFS(j, k);
                    }
                }
            }

            if (max_count < count) max_count = count;
        }

        System.out.println(max_count);
    }

    static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x >= 0 && y >= 0 && x < N && y < N) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
