package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2206 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][][] visited;
    static int[][] A;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i + 1][j + 1] = s.charAt(j) - '0';
            }
        }

        int result = BFS(1, 1);
        System.out.println(result);
    }

    private static int BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N + 1][M + 1][2];

        queue.add(new int[]{i, j, 0});
        visited[i][j][0] = true;

        int[][] distance = new int[N + 1][M + 1];
        distance[i][j] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int wallBroken = now[2];

            if (x == N && y == M) {
                return distance[x][y];
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx > 0 && ny > 0 && nx < N + 1 && ny < M + 1) {
                    if (A[nx][ny] == 0 && !visited[nx][ny][wallBroken]) {
                        visited[nx][ny][wallBroken] = true;
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.add(new int[]{nx, ny, wallBroken});
                    }

                    if (A[nx][ny] == 1 && wallBroken == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.add(new int[]{nx, ny, 1});
                    }
                }
            }
        }

        return -1;
    }
}
