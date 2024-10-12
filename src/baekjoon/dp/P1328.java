package baekjoon.dp;

import java.util.Scanner;

public class P1328 {
    static long mod = 1000000007;
    static long[][][] D = new long[101][101][101];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int L = scanner.nextInt();
        int R = scanner.nextInt();

        D[1][1][1] = 1;

        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < L + 1; j++) {
                for (int k = 1; k < R + 1; k++) {
                    D[i][j][k] = (D[i - 1][j][k] * (i - 2) + D[i - 1][j][k - 1] + D[i - 1][j - 1][k]) % mod;
                }
            }
        }

        System.out.println(D[N][L][R]);
    }
}
