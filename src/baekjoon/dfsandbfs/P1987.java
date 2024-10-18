package baekjoon.dfsandbfs;

import java.util.Scanner;

public class P1987 {

    static int R, C;
    static char[][] board;
    static boolean[] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxSteps = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = sc.next().toCharArray();
        }

        visited = new boolean[26];

        DFS(0, 0, 1);

        System.out.println(maxSteps);
    }

    static void DFS(int x, int y, int steps) {
        visited[board[x][y] - 'A'] = true;
        maxSteps = Math.max(maxSteps, steps);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[board[nx][ny] - 'A']) {
                DFS(nx, ny, steps + 1);
            }
        }

        visited[board[x][y] - 'A'] = false;
    }
}

