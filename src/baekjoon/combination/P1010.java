package baekjoon.combination;

import java.util.Scanner;

/**
 * 백준 1010 조합
 */
public class P1010 {
    static int N, K, T;
    static int[][] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        T = scanner.nextInt();

        D = new int[31][31];

        for (int i = 0; i < 31; i++) {
            D[i][0] = 1;
            D[i][1] = i;
            D[i][i] = 1;
        }

        for (int i = 2; i < 31; i++) {
            for (int j = 1; j < i; j++) {
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
            }
        }

        for (int i = 0; i < T; i++) {
            N = scanner.nextInt();
            K = scanner.nextInt();

            System.out.println(D[K][N]);
        }
    }
}
