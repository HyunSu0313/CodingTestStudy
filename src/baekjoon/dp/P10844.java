package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 10844 (DP 활용)
 * 쉬운 계단 수
 */
public class P10844 {
    static int mod = 1000000000;
    static long[][] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        D = new long[n + 1][11];

        for (int i = 1; i < 10; i++) {
            D[1][i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            D[i][0] = D[i - 1][1];
            D[i][9] = D[i - 1][8];

            for (int j = 1; j < 9; j++) {
                D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % mod;
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + D[n][i]) % mod;
        }
        System.out.println(sum);
    }
}
