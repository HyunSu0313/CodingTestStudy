package baekjoon.combination;

import java.util.Scanner;

/**
 * 백준 11050 조합 기본
 */
public class P11050 {
    static int N, K;
    static int[][] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();

        D = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }

        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < i; j++) {
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
            }
        }

        System.out.println(D[N][K]);
    }
}
