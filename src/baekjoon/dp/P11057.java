package baekjoon.dp;

import java.util.Scanner;

public class P11057 {
    static int[][] S;
    static int MOD = 10007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        S = new int[N + 1][10];

        for (int i = 0; i < 10; i++) {
            S[0][i] = 1;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    S[i][j] = (S[i][j] % MOD + S[i - 1][k] % MOD) % MOD;
                }
            }
        }

        System.out.println(S[N][9]);
    }
}
