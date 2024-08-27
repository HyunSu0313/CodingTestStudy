package baekjoon.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P10026 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int[][] A, B;
    static String[][] color;
    static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        scanner.nextLine();

        A = new int[N + 1][N + 1];
        B = new int[N + 1][N + 1];
        color = new String[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            String line = scanner.nextLine();
            for (int j = 1; j <= N; j++) {
                color[i][j] = String.valueOf(line.charAt(j - 1));
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (color[i][j].equals("R")) {
                    A[i][j] = 1;
                    B[i][j] = 1;
                } else if (color[i][j].equals("G")) {
                    A[i][j] = 2;
                    B[i][j] = 1;
                } else {
                    A[i][j] = 3;
                    B[i][j] = 2;
                }
            }
        }

        visited = new boolean[N + 1][N + 1];

        int countA = 0;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (A[i][j] == 1 && !visited[i][j]) {
                    countA++;
                    BFS(A, i, j, 1);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (A[i][j] == 2 && !visited[i][j]) {
                    countA++;
                    BFS(A, i, j, 2);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (A[i][j] == 3 && !visited[i][j]) {
                    countA++;
                    BFS(A, i, j, 3);
                }
            }
        }

        visited = new boolean[N + 1][N + 1];
        int countB = 0;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (B[i][j] == 1 && !visited[i][j]) {
                    countB++;
                    BFS(B, i, j, 1);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (B[i][j] == 2 && !visited[i][j]) {
                    countB++;
                    BFS(B, i, j, 2);
                }
            }
        }

        System.out.println(countA + " " + countB);

        scanner.close();
    }

    static void BFS(int[][] arr, int i, int j, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && y > 0 && x < N + 1 && y < N + 1) {
                    if (arr[x][y] == n && !visited[x][y]) {
                        visited[x][y] = true;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
