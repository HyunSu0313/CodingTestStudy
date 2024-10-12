package baekjoon.dp;

import java.util.Scanner;

public class P2342 {
    static int[][][] D = new int[100001][5][5];
    static int[][] mp = {{0, 2, 2, 2, 2}, {2, 1, 3, 4, 3}, {2, 3, 1, 3, 4}, {2, 4, 3, 1, 3}, {2, 3, 4, 3, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        int s = 1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 100001; k++) {
                    D[k][i][j] = 100001 * 4;
                }
            }
        }

        D[0][0][0] = 0;

        while (true) {
            n = scanner.nextInt();
            if (n == 0) break;

            for (int i = 0; i < 5; i++) {
                if (n == i) continue;
                for (int j = 0; j < 5; j++) {
                    D[s][i][n] = Math.min(D[s - 1][i][j] + mp[j][n], D[s][i][n]);
                }
            }

            for (int j = 0; j < 5; j++) {
                if (n == j) continue;
                for (int i = 0; i < 5; i++) {
                    D[s][n][j] = Math.min(D[s - 1][i][j] + mp[i][n], D[s][n][j]);
                }
            }
            s++;
        }

        s--;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, D[s][i][j]);
            }
        }

        System.out.println(min);
    }
}
