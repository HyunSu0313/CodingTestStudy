package baekjoon.dp;

import java.util.Scanner;

public class P16395 {
    static int[][] D;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        D = new int[N + 1][N + 1];
        D[1][1] = 1;
        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    D[i][j] = 1;
                } else if (j == i) {
                    D[i][j] = 1;
                } else {
                    D[i][j] = D[i - 1][j - 1] + D[i - 1][j];
                }
            }
        }

        System.out.println(D[N][K]);
    }
}
