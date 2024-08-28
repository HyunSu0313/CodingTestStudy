package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17836 (BFS 활용)
 * 공주님 구하기
 */
public class P17836 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int [][] A, B, distance;
    static boolean[][] visited;
    static int N, M;
    static int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        A = new int[N + 1][M + 1]; // 벽 뚫고 가는 방법
        B = new int[N + 1][M + 1]; // 벽 안뚫고 가는 방법

        int x = 0;
        int y = 0;

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                int num = Integer.parseInt(st.nextToken());
                A[i][j] = num;
                if (num != 2) {
                    B[i][j] = num;
                } else {
                    B[i][j] = 1;
                    x = i;
                    y = j;
                }
            }
        }

        boolean check1 = true;
        int count1 = 0;
        BFS(B, N, M);
        if (distance[N][M] == 0) check1 = false;
        if (check1) count1 = distance[N][M];
        else count1 = INF;

        boolean check2 = true;
        int count2 = 0;
        BFS(A, x, y);
        if (distance[x][y] == 0) check2 = false;
        if (check2) count2 = distance[x][y] + (N - x) + (M - y);
        else  count2 = INF;

        int result = Math.min(count1, count2);

        if (result <= k) System.out.println(result);
        else System.out.println("Fail");
    }

    static void BFS(int[][] array, int i, int j) {
        distance = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 1});
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[0] == i && now[1] == j) {
                return;
            }

            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && y > 0 && x < N + 1 && y < M + 1) {
                    if (array[x][y] != 1 && !visited[x][y]) {
                        visited[x][y] = true;
                        distance[x][y] = distance[now[0]][now[1]] + 1;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
