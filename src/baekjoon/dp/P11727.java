package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 11727 DP 활용
 */
public class P11727 {
    static long[] D;
    static long mod = 10007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        D = new long[1001];
        D[1] = 1;
        D[2] = 3;

        for (int i = 3; i < N + 1; i++) {
            D[i] = ((D[i - 2] * 2) % mod + D[i - 1] % mod) % mod;
        }

        System.out.println(D[N]);
    }
}
